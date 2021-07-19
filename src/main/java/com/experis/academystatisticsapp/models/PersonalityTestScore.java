package com.experis.academystatisticsapp.models;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PersonalityTestScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column
    private Date testDate;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
}
