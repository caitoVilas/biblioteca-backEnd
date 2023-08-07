package com.caito.app_biblioteca.repository;

import com.caito.app_biblioteca.model.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    boolean existsByDescription(String description);
    @Query("SELECT a FROM Area a WHERE a.id <> :id and a.description = :description")
    Area areaForDescription(Long id, String description);
}
