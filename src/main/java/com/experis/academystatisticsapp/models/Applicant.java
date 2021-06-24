package com.experis.academystatisticsapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "applicant_education", joinColumns = {@JoinColumn(name = "education_id")},
            inverseJoinColumns = {@JoinColumn(name = "applicant_id")})
    private Set<Education> educations;

    @Column
    private Integer studiedSemesters;

    @Column(nullable = false)
    private boolean movable;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "applicant_devLanguage", joinColumns = {@JoinColumn(name = "devLanguage_id")},
            inverseJoinColumns = {@JoinColumn(name = "applicant_id")})
    private Set<DevLanguage> devLanguages;

    @Column
    private Integer clsScore;

    @Column
    private Integer relativeClsScore;

    @Column
    private Integer switchScore;

    @Column
    private Integer relativeSwitchScore;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalityTestScore> personalityTestScores;

    @Column
    private Integer interviewScore;
}
