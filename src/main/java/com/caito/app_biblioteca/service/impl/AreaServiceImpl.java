package com.caito.app_biblioteca.service.impl;

import com.caito.app_biblioteca.exception.custom.BadRequestException;
import com.caito.app_biblioteca.exception.custom.NoContentException;
import com.caito.app_biblioteca.exception.custom.NotFoundException;
import com.caito.app_biblioteca.model.dto.AreaRequestDTO;
import com.caito.app_biblioteca.model.dto.AreaResponseDTO;
import com.caito.app_biblioteca.model.entity.Area;
import com.caito.app_biblioteca.model.entity.Editorial;
import com.caito.app_biblioteca.repository.AreaRepository;
import com.caito.app_biblioteca.service.contract.AreaService;
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
public class AreaServiceImpl implements AreaService {
    private final AreaRepository areaRepository;


    @Override
    public AreaResponseDTO create(AreaRequestDTO dto) {
        log.info("---> inicio servicio crear area");
        log.info("---> validando...");
        if (dto.getDescription().isEmpty()){
            log.error("ERROR: la descripcion para el area es requerida");
            throw new BadRequestException("la descripcion para el area es requerida");
        }
        if (areaRepository.existsByDescription(dto.getDescription())){
            log.error("ERROR: ya existe con area con la descripcion {}", dto.getDescription());
            throw new BadRequestException("ya existe una area con la descripcion " + dto.getDescription());
        }
        log.info("---> datos de area validados GUARDANDO...");
        Area area = Area.builder()
                .description(dto.getDescription())
                .status(true)
                .build();
        log.info("---> finalizado servicio crear area");
        return Utils.mapAreaToDTO(areaRepository.save(area));
    }

    @Override
    public AreaResponseDTO getById(Long id) {
        log.info("---> inicio servicio buscar area por id {}", id);
        log.info("---> buscando area...");
        Area area = areaRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra un area con id {}", id);
            throw new NotFoundException("no se encuentra un area con id " + id);
        });
        log.info("---> finalizado el servicio buscar area por id");
        return Utils.mapAreaToDTO(area);
    }

    @Override
    public List<AreaResponseDTO> getAll() {
        log.info("---> inicio servicio buscar todas las areas");
        log.info("---> buscando areas...");
        List<Area> areas = areaRepository.findAll();
        if (areas.isEmpty()){
            log.error("ERROR: no hay areas para mostrar");
            throw new NoContentException("no hay areas para mostrar");
        }
        log.info("---> finalizado servicio buscar areas");
        return areas.stream().map(Utils::mapAreaToDTO).collect(Collectors.toList());
    }

    @Override
    public AreaResponseDTO update(Long id, AreaRequestDTO dto) {
        log.info("---> inicio servicio actualizar area con id {}", id);
        log.info("---> validando...");
        Area areaVieja = areaRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra un area con id {}", id);
            throw new NotFoundException("no se encuentra un area con id " + id);
        });
        if (dto.getDescription().isEmpty()){
            log.error("ERROR: la descripcion para el area es requerida");
            throw new BadRequestException("la descripcion para el area es requerida");
        }
        Area otraArea = areaRepository.areaForDescription(id, dto.getDescription());
        if (otraArea != null){
            log.error("ERROR: ya existe otro area con esa descripcion {}", dto.getDescription());
            throw new BadRequestException("ya existe otro area con esa descripcion " + dto.getDescription());
        }
        log.info("---> datos de area validados GUARDANDO CAMBIOS....");
        Area area = Area.builder()
                .id(id)
                .description(dto.getDescription())
                .status(areaVieja.isStatus())
                .build();
        log.info("---> finalizado el servicio actualizar area");
        return Utils.mapAreaToDTO(area);
    }
}
