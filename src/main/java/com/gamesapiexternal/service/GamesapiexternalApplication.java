package com.gamesapiexternal.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GamesapiexternalApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamesapiexternalApplication.class, args);
    }

}
