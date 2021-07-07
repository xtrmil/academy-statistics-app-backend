package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.PersonalityTestScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalityTestScoreRepository extends JpaRepository<PersonalityTestScore, Long> {

    @Query(value = "SELECT * FROM Personality_test_score", nativeQuery = true)
    List<PersonalityTestScore> GetAll();
}
