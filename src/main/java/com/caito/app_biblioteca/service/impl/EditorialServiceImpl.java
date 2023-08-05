package com.caito.app_biblioteca.service.impl;

import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;
import com.caito.app_biblioteca.repository.EditorialRepository;
import com.caito.app_biblioteca.service.contract.EditorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class EditorialServiceImpl implements EditorialService {
    private final EditorialRepository editorialRepository;


    @Override
    public EditorialResponseDTO create(EditorialRequestDTO dto) {
        return null;
    }

    @Override
    public EditorialResponseDTO update(Long id, EditorialRequestDTO dto) {
        return null;
    }

    @Override
    public EditorialResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public List<EditorialResponseDTO> getAll() {
        return null;
    }
}
