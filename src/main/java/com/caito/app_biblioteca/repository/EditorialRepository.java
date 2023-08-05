package com.caito.app_biblioteca.repository;

import com.caito.app_biblioteca.model.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    boolean existsByName(String name);
    @Query("SELECT e FROM Editorial e WHERE e.id <> :id and e.name = :name")
    Editorial nameExists(Long id, String name);
}
