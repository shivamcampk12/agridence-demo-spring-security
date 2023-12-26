package com.agridence.microservice.Assignment;

import com.agridence.microservice.Assignment.Service.UserServiceInterface;
import com.agridence.microservice.Assignment.Repository.RoleRepository;
import com.agridence.microservice.Assignment.Repository.UserRepository;
import com.agridence.microservice.Assignment.Repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityLayerJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityLayerJwtApplication.class, args);

    }

     @Bean
    CommandLineRunner run (UserServiceInterface userServiceInterface, RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, NoteRepository noteRepository)
    {
        return  args ->
    {   //iUserService.saveRole(new Role(RoleName.USER));
        //iUserService.saveRole(new Role(RoleName.ADMIN));

       // iUserService.saverUser(new User("testusername", passwordEncoder.encode("password"), new ArrayList<>()));
       // iUserService.saverUser(new User("testadminname", passwordEncoder.encode("password"), new ArrayList<>()));

//        Role role = iRoleRepository.findByRoleName(RoleName.ADMIN);
        //User user = iUserRepository.findByUsername("testadminname").orElse(null);
        //user.getRoles().add(role);
        //iUserService.saverUser(user);

  //      User userr = iUserRepository.findByUsername("testusername").orElse(null);
   //     Role rolee = iRoleRepository.findByRoleName(RoleName.USER);
     //   userr.getRoles().add(rolee);
      //  iUserService.saverUser(userr);

    };
    }

}


