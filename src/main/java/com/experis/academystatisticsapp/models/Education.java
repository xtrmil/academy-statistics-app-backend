package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "educations")
    private Set<Location> locations;

    @ManyToMany(mappedBy = "educations")
    private Set<Applicant> applicants;



}
