package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.DevLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DevLanguageRepository extends JpaRepository<DevLanguage, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Dev_language (language) VALUES (?1)", nativeQuery = true)
    void createDevLanguage(String language);

    @Query(value = "SELECT * FROM Dev_language WHERE dev_language.id = ?1", nativeQuery = true)
    DevLanguage getDevLanguageById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Dev_language SET language = ?2 WHERE dev_language.id = ?1", nativeQuery = true)
    void updateDevLanguageById(Long id, String devLanguage);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM dev_language WHERE dev_language.id = ?1", nativeQuery = true)
    void deleteDevLanguageById(Long id);

    @Query(value = "SELECT * FROM dev_language", nativeQuery = true)
    List<DevLanguage> GetAll();
}
