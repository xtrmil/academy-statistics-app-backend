package com.experis.academystatisticsapp.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
