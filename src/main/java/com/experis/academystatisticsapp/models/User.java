package com.experis.academystatisticsapp.models;

import lombok.Data; // Lombok, tool for auto generation of getters and setters by using @Data in model class

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private boolean isAdmin = false;
}
