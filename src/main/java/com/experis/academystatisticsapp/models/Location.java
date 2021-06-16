package com.experis.academystatisticsapp.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String name;
}

