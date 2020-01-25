package com.onthepitch.backend.init;

import org.springframework.boot.SpringApplication;

public class EntryPoint {

    private EntryPoint(){}

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfig.class,args);
    }
}
