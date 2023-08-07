package com.caito.app_biblioteca.repository;

import com.caito.app_biblioteca.model.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    boolean existsByName(String name);
    boolean existsByEmail(String email);
    @Query("SELECT l FROM Library l WHERE l.id <> :id and l.name = :name")
    Library libraryByName(Long id, String name);
}
