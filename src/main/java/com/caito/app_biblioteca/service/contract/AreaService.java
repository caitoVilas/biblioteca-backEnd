package com.caito.app_biblioteca.service.contract;

import com.caito.app_biblioteca.model.dto.AreaRequestDTO;
import com.caito.app_biblioteca.model.dto.AreaResponseDTO;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

public interface AreaService {
    AreaResponseDTO create(AreaRequestDTO dto);
    AreaResponseDTO getById(Long id);
    List<AreaResponseDTO> getAll();
    AreaResponseDTO update(Long id, AreaRequestDTO dto);
}
