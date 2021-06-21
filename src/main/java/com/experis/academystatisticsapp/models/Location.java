package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "education_location", joinColumns = {@JoinColumn(name = "education_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private Set<Education> educations;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "university_location", joinColumns = {@JoinColumn(name = "university_id")},
            inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private Set<University> universities;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments;
}

