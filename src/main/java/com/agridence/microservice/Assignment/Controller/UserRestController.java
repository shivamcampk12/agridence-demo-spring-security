package com.agridence.microservice.Assignment.Controller;


import com.agridence.microservice.Assignment.Service.UserServiceInterface;
import com.agridence.microservice.Assignment.Vo.LoginRequest;
import com.agridence.microservice.Assignment.Vo.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {


    private final UserServiceInterface userServiceInterface;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return userServiceInterface.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) {
        return userServiceInterface.authenticate(loginRequest);
    }


}
