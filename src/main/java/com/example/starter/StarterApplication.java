package com.example.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableJpaRepositories
@EnableCaching
public class StarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }

}
