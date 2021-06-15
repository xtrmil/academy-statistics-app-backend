package com.experis.academystatisticsapp.models;

import lombok.Data;

import java.util.Set;

@Data
public class Education {
    private long Id;
    private String name;
    private Set<EducationLocation> location;
}
