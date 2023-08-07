package com.caito.app_biblioteca.repository;

import com.caito.app_biblioteca.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitle(String title);
    boolean existsByIsbn(String isbn);
    @Query("SELECT b FROM Book b WHERE b.id <> :id AND b.isbn = :isbn")
    Book searchIsbn(Long id, String isbn);
}
