package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education,Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Education (name) VALUES (?1)", nativeQuery = true)
    void createEducation(String name);

    @Query(value = "SELECT * FROM Education WHERE education.id = ?1", nativeQuery = true)
    Education getEducationById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM Education WHERE education.id =?1), 'true','false')", nativeQuery = true)
    boolean existsById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Education SET name = ?2 WHERE education.id = ?1", nativeQuery = true)
    void updateEducationById(Long id,String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Education WHERE education.id = ?1", nativeQuery = true)
    void deleteEducationById(Long id);

    @Query(value = "SELECT * FROM Education", nativeQuery = true)
    List<Education> GetAll();
}
