package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "educations")
    private Set<Location> locations;

}
