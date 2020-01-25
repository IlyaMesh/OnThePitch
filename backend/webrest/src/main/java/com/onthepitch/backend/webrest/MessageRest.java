package com.onthepitch.backend.webrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class MessageRest implements MessageController {
    @GetMapping("/message")
    @Override
    public String getMesaage() {
        return "Test string";
    }
}
