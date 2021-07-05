package com.experis.academystatisticsapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyName;

    @OneToMany(mappedBy = "assignment")
    private List<Applicant> applicants;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
