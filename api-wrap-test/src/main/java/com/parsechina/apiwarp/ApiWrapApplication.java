package com.parsechina.apiwarp;

import com.seelyn.apiwrap.WrapStore;
import com.seelyn.apiwrap.annotation.EnableApiWrap;
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
        wrapStore.putSecret("eqxiu", "eqxiu");
    }
}
