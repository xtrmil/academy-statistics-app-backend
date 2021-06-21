package com.experis.academystatisticsapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class DevLanguage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String language;

   @ManyToMany(mappedBy = "devLanguages")
   private Set<Applicant> applicants;
}
