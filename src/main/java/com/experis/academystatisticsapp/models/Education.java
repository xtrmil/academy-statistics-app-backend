package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "education_location", joinColumns = {@JoinColumn(name = "education_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private List<Location> locations;
}
