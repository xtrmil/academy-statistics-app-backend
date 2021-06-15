package com.experis.academystatisticsapp.models;

import com.experis.academystatisticsapp.models.enums.AppliedLanguage;
import lombok.Data;

import java.util.Map;

@Data
public class Applicant {
    private long id;
    private Education education;
    private EducationLocation educationLocation;
    private int studiedSemesters;
    private boolean movable;
    private AppliedLanguage appliedLanguage;

    private int clsScore;
    private int relativeClsScore;
    private int switchScore;
    private int relativeSwitchScore;
    private Map personalityTestScore;
    private int interviewScore;
}
