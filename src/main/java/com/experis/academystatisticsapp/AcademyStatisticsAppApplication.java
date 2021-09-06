package com.experis.academystatisticsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class AcademyStatisticsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademyStatisticsAppApplication.class, args);
    }

}
