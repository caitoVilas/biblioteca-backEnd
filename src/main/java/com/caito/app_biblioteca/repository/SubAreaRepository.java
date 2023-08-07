package com.caito.app_biblioteca.repository;

import com.caito.app_biblioteca.model.entity.Area;
import com.caito.app_biblioteca.model.entity.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Repository
public interface SubAreaRepository extends JpaRepository<SubArea, Long> {
    boolean existsByDescription(String description);
    @Query("SELECT s FROM SubArea s WHERE s.id <> :id and s.description = :description")
    SubArea subareaForDescription(Long id, String description);
    boolean existsByIdAndDescription(Long id, String description);
}
