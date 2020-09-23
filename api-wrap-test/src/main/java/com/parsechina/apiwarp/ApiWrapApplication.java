package com.parsechina.apiwarp;

import com.seelyn.apiwrap.annotation.EnableApiWrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApiWrap
@SpringBootApplication
public class ApiWrapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiWrapApplication.class, args);
    }

}
