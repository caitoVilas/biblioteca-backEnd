package com.caito.app_biblioteca.service.contract;

import com.caito.app_biblioteca.model.dto.LibrarieRequestDTO;
import com.caito.app_biblioteca.model.dto.LibraryResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

public interface LibraryService {
    LibraryResponseDTO create(LibrarieRequestDTO dto);
    LibraryResponseDTO getById(Long id);
    List<LibraryResponseDTO> getAll();
    LibraryResponseDTO update(Long id, LibrarieRequestDTO dto);
}
