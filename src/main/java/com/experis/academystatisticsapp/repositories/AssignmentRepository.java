package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Assignment (name,company_name, location_id) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void createAssignment(String name, String companyName, Long id);

    @Query(value = "SELECT * FROM Assignment WHERE assignment.id = ?1", nativeQuery = true)
    Assignment getAssignmentById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM Assignment WHERE assignment.id =?1), 'true','false')", nativeQuery = true)
    boolean existsById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Assignment SET name = ?2, company_name = ?3, location_id = ?4 WHERE assignment.id = ?1", nativeQuery = true)
    void updateAssignmentById(Long id,String name, String companyName, Long locationId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Assignment WHERE Assignment.id = ?1", nativeQuery = true)
    void deleteAssignmentById(Long id);

    @Query(value = "SELECT * FROM Assignment", nativeQuery = true)
    List<Assignment> getAll();

}
