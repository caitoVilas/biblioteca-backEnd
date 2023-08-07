package com.caito.app_biblioteca.service.contract;

import com.caito.app_biblioteca.model.dto.BookRequestDTO;
import com.caito.app_biblioteca.model.dto.BookResponseDTO;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */
public interface BookService {
    BookResponseDTO create(BookRequestDTO dto);
    BookResponseDTO getById(Long id);
    List<BookResponseDTO> getAll();
    BookResponseDTO update(Long id, BookRequestDTO dto);
}
