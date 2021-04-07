package com.cc.develop.third.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ClientThirdApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientThirdApplication.class, args);
    }

}
