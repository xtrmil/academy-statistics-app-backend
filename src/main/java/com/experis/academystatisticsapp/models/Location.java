package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "location")
    private List<Education> educations;

    @ManyToMany
    @JoinColumn(name = "university_id")
    private List<University> universities;

    @OneToMany(mappedBy = "location")
    private List<Assignment> assignments;
}

