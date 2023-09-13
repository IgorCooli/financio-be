package com.io.financio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FinancioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancioApplication.class, args);
    }

}