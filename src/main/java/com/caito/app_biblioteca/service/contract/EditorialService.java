package com.caito.app_biblioteca.service.contract;

import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 09/2023
 */
public interface EditorialService {
    EditorialResponseDTO create(EditorialRequestDTO dto);
    EditorialResponseDTO update(Long id, EditorialRequestDTO dto);
    EditorialResponseDTO getById(Long id);
    List<EditorialResponseDTO> getAll();
}
