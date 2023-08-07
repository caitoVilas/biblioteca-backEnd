package com.caito.app_biblioteca.service.impl;

import com.caito.app_biblioteca.exception.custom.BadRequestException;
import com.caito.app_biblioteca.exception.custom.NoContentException;
import com.caito.app_biblioteca.exception.custom.NotFoundException;
import com.caito.app_biblioteca.model.dto.LibrarieRequestDTO;
import com.caito.app_biblioteca.model.dto.LibraryResponseDTO;
import com.caito.app_biblioteca.model.entity.Library;
import com.caito.app_biblioteca.repository.LibraryRepository;
import com.caito.app_biblioteca.service.contract.LibraryService;
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
public class LibraryServiceimpl implements LibraryService {
    private final LibraryRepository libraryRepository;


    @Override
    public LibraryResponseDTO create(LibrarieRequestDTO dto) {
        log.info("---> inicio servicio crear libreria");
        log.info("---> validando...");
        if (dto.getName().isEmpty()){
            log.error("ERROR: la descripcion para la libreria es requerida");
            throw new BadRequestException("la descripcion para la libreria es requerida");
        }
        if (libraryRepository.existsByName(dto.getName())){
            log.error("ERROR: ya existe una libreria con el nombre {}", dto.getName());
            throw new BadRequestException("ya existe una libreria con el nombre " + dto.getName());
        }
        if (dto.getAddress().isEmpty()){
            log.error("ERROR: la direccion para la libreria es requerida");
            throw new BadRequestException("la direccion para la libreria es requerida");
        }
        if (dto.getEmail().isEmpty()){
            log.error("ERROR: el email para la libreria es requerido");
            throw new BadRequestException("el email para la libreria es requerido");
        }
        log.info("---> datos de libreria validados GUARDANDO...");
        Library library = Utils.mapToLibraryEntity(dto);
        library.setStaus(true);
        log.info("---> finalizado servicio crear area");
        return Utils.mapLibraryToDto(libraryRepository.save(library));
    }

    @Override
    public LibraryResponseDTO getById(Long id) {
        log.info("---> inicio servicio buscar libreria por id {}", id);
        log.info("---> buscando libreria...");
        Library library = libraryRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra una libreria con id {}", id);
            return new NotFoundException("no se encuentra una libreria con id " + id);
        });
        log.info("---> finalizado el servicio buscar libreria por id");
        return Utils.mapLibraryToDto(library);
    }

    @Override
    public List<LibraryResponseDTO> getAll() {
        log.info("---> inicio servicio buscar todas las librerias");
        log.info("---> buscando librerias...");
        List<Library> libraries = libraryRepository.findAll();
        if (libraries.isEmpty()){
            log.error("ERROR: no hay librerias para mostrar");
            throw new NoContentException("no hay librerias para mostrar");
        }
        log.info("---> finalizado servicio buscar librerias");
        return libraries.stream().map(Utils::mapLibraryToDto).collect(Collectors.toList());
    }

    @Override
    public LibraryResponseDTO update(Long id, LibrarieRequestDTO dto) {
        log.info("---> inicio servicio actualizar libreria con id {}", id);
        log.info("---> validando...");
        Library libraryOld = libraryRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra una libreria con id {}", id);
            return new NotFoundException("no se encuentra una libreria con id " + id);
        });
        if (dto.getName().isEmpty()){
            log.error("ERROR: el nombre para la libreria es requerida");
            throw new BadRequestException("el nombre para la libreria es requerida");
        }
        if (dto.getEmail().isEmpty()){
            log.error("ERROR: el email para la libreria es requerida");
            throw new BadRequestException("el email para la libreria es requerida");
        }
        Library otraLibrary = libraryRepository.libraryByName(id, dto.getName());
        if (otraLibrary != null){
            log.error("ERROR: ya existe otra libreria con ese nombre {}", dto.getName());
            throw new BadRequestException("ya existe otra libreriaa con ese nombre " + dto.getName());
        }
        if (libraryRepository.existsByEmail(dto.getEmail())){
            log.error("ERROR: ya existe otra libreria con ese email {}", dto.getEmail());
            throw new BadRequestException("ya existe otra libreriaa con ese email " + dto.getEmail());
        }
        log.info("---> datos de libreria validados GUARDANDO CAMBIOS....");
        Library library = Utils.mapToLibraryEntity(dto);
        library.setStaus(libraryOld.isStaus());
        log.info("---> finalizado el servicio actualizar area");
        return Utils.mapLibraryToDto(library);
    }
}
