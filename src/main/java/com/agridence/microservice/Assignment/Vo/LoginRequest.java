package com.agridence.microservice.Assignment.Vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    //it's a Data Trasfer Object for Login
    private String username;
    private String password;
}
