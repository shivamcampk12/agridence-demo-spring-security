package com.agridence.microservice.Assignment.Service;

import com.agridence.microservice.Assignment.Vo.BearerToken;
import com.agridence.microservice.Assignment.Vo.LoginRequest;
import com.agridence.microservice.Assignment.Vo.RegisterRequest;
import com.agridence.microservice.Assignment.Entity.Role;
import com.agridence.microservice.Assignment.Entity.RoleName;
import com.agridence.microservice.Assignment.Entity.User;
import com.agridence.microservice.Assignment.Repository.RoleRepository;
import com.agridence.microservice.Assignment.Repository.UserRepository;
import com.agridence.microservice.Assignment.Configuration.JWTUtility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtility JWTUtility;


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User saverUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new ResponseEntity<>("This username is not available !", HttpStatus.SEE_OTHER);
        } else {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setName(registerRequest.getName());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            Role role = roleRepository.findByRoleName(RoleName.USER);
            user.setRoles(Collections.singletonList(role));

            userRepository.save(user);
            //String token = jwtUtilities.generateToken(registerRequest.getUsername(),Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

        }
    }

    @Override
    public ResponseEntity<?> authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("invalid username/password User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));

        String token = JWTUtility.generateToken(user.getUsername(), rolesNames);
        return new ResponseEntity<>(new BearerToken("Bearer " + token, "JWT"), HttpStatus.OK);
    }


    @Override
    public User fetchUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found !"));
    }
}

