package com.agridence.microservice.Assignment;

import com.agridence.microservice.Assignment.Entity.Role;
import com.agridence.microservice.Assignment.Entity.RoleName;
import com.agridence.microservice.Assignment.Entity.User;
import com.agridence.microservice.Assignment.Service.UserServiceInterface;
import com.agridence.microservice.Assignment.Repository.RoleRepository;
import com.agridence.microservice.Assignment.Repository.UserRepository;
import com.agridence.microservice.Assignment.Repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AgridenceDemoSpringSecurity {

    public static void main(String[] args) {
        SpringApplication.run(AgridenceDemoSpringSecurity.class, args);

    }

     @Bean
    CommandLineRunner run (UserServiceInterface userServiceInterface, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, NoteRepository noteRepository)
    {
        return  args ->
    {
        userServiceInterface.saveRole(new Role(RoleName.USER));
        userServiceInterface.saveRole(new Role(RoleName.ADMIN));

        userServiceInterface.saverUser(new User("testusername", passwordEncoder.encode("password"), new ArrayList<>()));
        userServiceInterface.saverUser(new User("testadminname", passwordEncoder.encode("password"), new ArrayList<>()));

        Role role = roleRepository.findByRoleName(RoleName.ADMIN);
        User user = userRepository.findByUsername("testadminname").orElse(null);
        user.getRoles().add(role);
        userServiceInterface.saverUser(user);

        User userr = userRepository.findByUsername("testusername").orElse(null);
        Role rolee = roleRepository.findByRoleName(RoleName.USER);
        userr.getRoles().add(rolee);
        userServiceInterface.saverUser(userr);

    };
    }

}


