package com.agridence.microservice.Assignment.Service;


import com.agridence.microservice.Assignment.Vo.LoginRequest;
import com.agridence.microservice.Assignment.Vo.RegisterRequest;
import com.agridence.microservice.Assignment.Entity.Role;
import com.agridence.microservice.Assignment.Entity.User;
import org.springframework.http.ResponseEntity;


public interface UserServiceInterface {

    ResponseEntity<?> authenticate(LoginRequest loginRequest);

    ResponseEntity<?> register(RegisterRequest registerRequest);

    Role saveRole(Role role);

    User saverUser(User user);

    User fetchUserByUsername(String username);
}
