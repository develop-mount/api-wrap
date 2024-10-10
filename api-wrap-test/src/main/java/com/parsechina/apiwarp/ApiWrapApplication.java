package com.parsechina.apiwarp;

import icu.develop.api.wrap.WrapStore;
import icu.develop.api.wrap.annotation.EnableApiWrap;
import icu.develop.api.wrap.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApiWrap
@SpringBootApplication
public class ApiWrapApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ApiWrapApplication.class, args);
    }

    @Autowired
    private WrapStore wrapStore;

    @Override
    public void run(String... args) throws Exception {
        wrapStore.putSecret("eqxiu", new SecretKey("sdad", "sdad"));
    }
}
