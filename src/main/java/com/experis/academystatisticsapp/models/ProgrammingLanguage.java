package com.experis.academystatisticsapp.models;

import javax.persistence.*;

@Entity
public class ProgrammingLanguage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String language;


}
