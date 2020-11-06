package com.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Imania",version = "2.0",description = "Imania Micro-service "))
public class ImaniaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImaniaApplication.class, args);
    }

}
