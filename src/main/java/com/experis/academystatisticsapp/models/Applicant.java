package com.experis.academystatisticsapp.models;

import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class Applicant {
    private long id;
    private Education education;
    private Location location;
    private int studiedSemesters;
    private boolean movable;
    private Set<ProgrammingLanguage> programmingLanguages;

    private int clsScore;
    private int relativeClsScore;
    private int switchScore;
    private int relativeSwitchScore;
    private Map personalityTestScore;
    private int interviewScore;
}
