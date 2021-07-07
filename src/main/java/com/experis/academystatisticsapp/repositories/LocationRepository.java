package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Location (name) VALUES (?1)", nativeQuery = true)
    void createLocation(String name);

    @Query(value = "SELECT * FROM Location WHERE location.id = ?1", nativeQuery = true)
    Location getLocationById(Long id);

    @Query(value = "SELECT IF(EXISTS (SELECT * FROM Location WHERE location.id =?1), 'true','false')", nativeQuery = true)
    boolean existsById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Location SET name = ?2 WHERE location.id = ?1", nativeQuery = true)
    void updateLocationById(Long id,String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Location WHERE location.id = ?1", nativeQuery = true)
    void deleteLocationById(Long id);

    @Query(value = "SELECT * FROM Location", nativeQuery = true)
    List<Location> GetAll();
}
