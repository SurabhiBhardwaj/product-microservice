package com.example.productMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;

@SpringBootApplication
public class ProductMicroserviceApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ProductMicroserviceApplication.class, args);
    }
}