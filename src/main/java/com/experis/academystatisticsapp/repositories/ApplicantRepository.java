package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Applicant " +
            "(cls_score, " +
            "interview_score, " +
            "movable, " +
            "relative_cls_score, " +
            "relative_switch_score, " +
            "studied_semesters, " +
            "switch_score) " +
            "VALUES (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void createApplicant(Integer clsScore,
                         Integer interviewScore,
                         boolean movable,
                         Integer relativeClsScore,
                         Integer relativeSwitchScore,
                         Integer studiedSemester,
                         Integer switchScore);

    @Query(value = "SELECT * FROM Applicant WHERE applicant.id = ?1", nativeQuery = true)
    Applicant getApplicantById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM Applicant WHERE applicant.id =?1), 'true','false')", nativeQuery = true)
    boolean existsById(Long id);

    /**TODO*/
    @Transactional
    @Modifying
    @Query(value = "REPLACE INTO Applicant " +
            "(cls_score, " +
            "interview_score, " +
            "movable, " +
            "relative_cls_score, " +
            "relative_switch_score, " +
            "studied_semesters, " +
            "switch_score) " +
            "VALUES (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    void updateApplicantById(Integer clsScore,
                         Integer interviewScore,
                         boolean movable,
                         Integer relativeClsScore,
                         Integer relativeSwitchScore,
                         Integer studiedSemester,
                         Integer switchScore);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Applicant WHERE applicant.id = ?1", nativeQuery = true)
    void deleteApplicantById(Long id);

    @Query(value = "SELECT * FROM Applicant", nativeQuery = true)
    List<Applicant> GetAll();

}
