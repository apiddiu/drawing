package com.aldo.drawing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DrawingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DrawingApplication.class, args);
    }
}
