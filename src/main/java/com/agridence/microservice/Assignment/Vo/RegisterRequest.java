package com.agridence.microservice.Assignment.Vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest implements Serializable {

    String name;
    String username;
    String password;
}
