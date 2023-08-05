package com.caito.app_biblioteca.service.impl;

import com.caito.app_biblioteca.exception.custom.BadRequestException;
import com.caito.app_biblioteca.exception.custom.NoContentException;
import com.caito.app_biblioteca.exception.custom.NotFoundException;
import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;
import com.caito.app_biblioteca.model.entity.Editorial;
import com.caito.app_biblioteca.repository.EditorialRepository;
import com.caito.app_biblioteca.service.contract.EditorialService;
import com.caito.app_biblioteca.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        log.info("---> inicio servicio crear editorial");
        log.info("---> validando...");
        if (dto.getName().isEmpty()){
            log.error("ERROR: el nombre de la editorial es requerido");
            throw new BadRequestException("el nombre de la editorial es requerido");
        }
        if (editorialRepository.existsByName(dto.getName())){
            log.error("ERROR: ya existe una editorial con ese nombre");
            throw new BadRequestException("ya existe una editorial con ese nombre");
        }
        log.info("---> datos de editorial validados GUARDANDO...");
        Editorial editorial = Editorial.builder()
                .name(dto.getName())
                .state(true)
                .build();
        return Utils.mapEditorialToDto(editorialRepository.save(editorial));
    }

    @Override
    public EditorialResponseDTO update(Long id, EditorialRequestDTO dto) {
        log.info("---> inicio servicio actualizar editorial con id {}", id);
        log.info("---> validando...");
        editorialRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: la editorial con id {} no se encuentra", id);
            return new NotFoundException("la editorial con id " + id + " no se encuentra");
        });
        Editorial otraEditorial = editorialRepository.findByNameExists(id, dto.getName());
        if (otraEditorial != null){
            log.error("ERROR: ya existe otra editorial con el nombre {}", dto.getName());
            throw new BadRequestException("ya existe una editorial con el nombre " + dto.getName());
        }
        log.info("---> datos de editorial validados GUARDANDO CAMBIOS");
        Editorial editorial = Editorial.builder()
                .id(id)
                .name(dto.getName())
                .state(true)
                .build();
        return Utils.mapEditorialToDto(editorialRepository.save(editorial));
    }

    @Override
    public EditorialResponseDTO getById(Long id) {
        log.info("---> inicio servicio buscar editorial x id {}", id);
        log.info("---> buscando editorial...");
        Editorial editorial = editorialRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: la editorial con id {} no se encuentra", id);
            return new NotFoundException("la editorial con id " + id + " no se encuentra");
        });
        return Utils.mapEditorialToDto(editorial);
    }

    @Override
    public List<EditorialResponseDTO> getAll() {
        log.info("---> inicio servicio buscar todas las editoriales");
        log.info("---> buscando editoriales...");
        List<Editorial> editorials = editorialRepository.findAll();
        if (editorials.isEmpty()){
            log.error("ERROR: no hay editoriales para mostrar");
            throw new NoContentException("no hay editoriales para mostrar");
        }
        return editorials.stream().map(Utils::mapEditorialToDto).collect(Collectors.toList());
    }
}
