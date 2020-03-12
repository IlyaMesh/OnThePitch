package com.onthepitch.backend.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BasicAuthController {
    @GetMapping("/basicauth")
    public String basicAuth() {
        return "Success";
    }
}
