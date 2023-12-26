package com.agridence.microservice.Assignment.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/superadmin")
@RequiredArgsConstructor
public class SuperadminRestController {

    @GetMapping("/hi")
    public String sayHi() {
        return "Super Admin URL Test";
    }


}
