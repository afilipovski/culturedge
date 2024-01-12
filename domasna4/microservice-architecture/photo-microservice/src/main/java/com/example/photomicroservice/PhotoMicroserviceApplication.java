package com.example.photomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PhotoMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoMicroserviceApplication.class, args);
    }

}
