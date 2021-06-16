package com.experis.academystatisticsapp.models;

import lombok.Data;

import java.util.Set;

@Data
public class Education {
    private Long Id;
    private String name;
    private Set<Location> locations;
}
