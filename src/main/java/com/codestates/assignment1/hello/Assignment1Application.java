package com.codestates.assignment1.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Assignment1Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Assignment1Application.class, args);

    }
}

