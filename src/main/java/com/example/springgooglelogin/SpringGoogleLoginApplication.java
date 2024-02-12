package com.example.springgooglelogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringGoogleLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGoogleLoginApplication.class, args);
    }

}
