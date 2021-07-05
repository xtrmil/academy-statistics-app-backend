package com.experis.academystatisticsapp.repositories;

import com.experis.academystatisticsapp.models.DevLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevLanguageRepository extends JpaRepository<DevLanguage, Long> {
}
