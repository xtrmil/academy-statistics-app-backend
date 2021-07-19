package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.PersonalityTestScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface PersonalityTestScoreRepository extends JpaRepository<PersonalityTestScore, Long> {

    @Query(value = "SELECT * FROM Personality_test_score", nativeQuery = true)
    List<PersonalityTestScore> getAllPersonalityTestScores();

    @Query(value = "SELECT * FROM Personality_test_score WHERE personality_test_score.id = ?1", nativeQuery = true)
    PersonalityTestScore getPersonalityTestScoreById(Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Personality_test_score " +
            "(score, " +
            "test_date, " +
            "applicant_id) " +
            "VALUES (?1,?2,?3)", nativeQuery = true)
    void createPersonalityTestScore(Integer score, Date testDate,Long applicantId);

    @Transactional
    @Modifying
    @Query(value = "REPLACE INTO Personality_Test_score" +
            "(score, " +
            "test_date, " +
            "applicant_id) " +
            "VALUES (?1,?2,?3)", nativeQuery = true)
    void updatePersonalityTestScore(Integer score, Date testDate,Long applicantId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Personality_test_score WHERE personality_test_score.id = ?1", nativeQuery = true)
    void deleteTestScoreById(Long id);
}
