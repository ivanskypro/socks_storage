package com.example.socksstorage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition

public class SocksstorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocksstorageApplication.class, args);
    }

}
