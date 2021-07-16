package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer studiedSemesters;

    @Column(nullable = false)
    private boolean movable;

    @Column
    private Integer clsScore;

    @Column
    private Integer relativeClsScore;

    @Column
    private Integer switchScore;

    @Column
    private Integer relativeSwitchScore;

    @Column
    private Integer interviewScore;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonalityTestScore> personalityTestScores;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "applicant_devLanguage", joinColumns = {@JoinColumn(name = "devLanguage_id")},
            inverseJoinColumns = {@JoinColumn(name = "applicant_id")})
    private Set<DevLanguage> devLanguages;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "applicant_education", joinColumns = {@JoinColumn(name = "education_id")},
            inverseJoinColumns = {@JoinColumn(name = "applicant_id")})
    private Set<Education> educations;
}
