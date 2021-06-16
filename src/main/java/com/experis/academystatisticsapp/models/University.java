package com.experis.academystatisticsapp.models;

import lombok.Data;

import java.util.Set;

@Data
public class University {
    private Long id;
    private String name;
    private Set<Location> locations;
}
