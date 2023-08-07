package com.caito.app_biblioteca.service.contract;

import com.caito.app_biblioteca.model.dto.SubAreaRequestDTO;
import com.caito.app_biblioteca.model.dto.SubAreaResponseDTO;

import java.util.List;

public interface SubAreaService {
    SubAreaResponseDTO create(SubAreaRequestDTO dto);
    SubAreaResponseDTO getById(Long id);
    List<SubAreaResponseDTO> getAll();
    SubAreaResponseDTO update(Long id, SubAreaRequestDTO dto);
}
