package com.experis.academystatisticsapp.models;

import lombok.Data; // Lombok, tool for auto generation of getters and setters by using @Data in model class

@Data
public class User {
    private long id;
    private String email;
    private String password;
    private boolean isAdmin = false;
}
