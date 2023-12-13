package com.example.dsca2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Dsca2Application {

    public static void main(String[] args) {
        SpringApplication.run(Dsca2Application.class, args);
    }
}
