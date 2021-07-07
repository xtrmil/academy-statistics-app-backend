package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO University (name) VALUES (?1)", nativeQuery = true)
    void createUniversity(String name);

    @Query(value = "SELECT * FROM University WHERE university.id = ?1", nativeQuery = true)
    University getUniversityById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM University WHERE university.id =?1), 'true','false')", nativeQuery = true)
    boolean existsById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE University SET name = ?2 WHERE university.id = ?1", nativeQuery = true)
    void updateUniversityById(Long id,String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM University WHERE university.id = ?1", nativeQuery = true)
    void deleteUniversityById(Long id);

    @Query(value = "SELECT * FROM University", nativeQuery = true)
    List<University> GetAll();
}
