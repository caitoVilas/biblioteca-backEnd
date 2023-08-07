package com.caito.app_biblioteca.service.impl;

import com.caito.app_biblioteca.exception.custom.BadRequestException;
import com.caito.app_biblioteca.exception.custom.NoContentException;
import com.caito.app_biblioteca.exception.custom.NotFoundException;
import com.caito.app_biblioteca.model.dto.SubAreaRequestDTO;
import com.caito.app_biblioteca.model.dto.SubAreaResponseDTO;
import com.caito.app_biblioteca.model.entity.Area;
import com.caito.app_biblioteca.model.entity.SubArea;
import com.caito.app_biblioteca.repository.AreaRepository;
import com.caito.app_biblioteca.repository.SubAreaRepository;
import com.caito.app_biblioteca.service.contract.SubAreaService;
import com.caito.app_biblioteca.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date: 08/20323
 * */

@Service
@Slf4j
@RequiredArgsConstructor
public class SubAreaServiceImpl implements SubAreaService {
    private final SubAreaRepository subAreaRepository;
    private final AreaRepository areaRepository;


    @Override
    public SubAreaResponseDTO create(SubAreaRequestDTO dto) {
        log.info("---> inicio servicio crear subarea");
        log.info("---> validando...");
        if (dto.getDescription().isEmpty()){
            log.error("ERROR: la descripcion para la subares es requerida");
            throw new BadRequestException("la descripcion para la subarea es requerida");
        }
        if (subAreaRepository.existsByDescription(dto.getDescription())){
            log.error("ERROR: ya existe un subarea con la descripcion {}", dto.getDescription());
            throw new BadRequestException("ya existe un subarea con la descripcion " + dto.getDescription());
        }
        Area area = areaRepository.findById(dto.getIdArea()).orElseThrow(()->{
            log.error("ERROR: no se encuentra un area con id {}", dto.getIdArea());
            return new BadRequestException("no se encuentra un area con id " + dto.getIdArea());
        });
        if (subAreaRepository.existsByIdAndDescription(dto.getIdArea(), dto.getDescription())){
            log.error("este subarea {} ya existe en esa area", dto.getDescription());
            throw new BadRequestException("esta subarea " + dto.getDescription() + "ya exite en esa area");
        }
        log.info("---> datos de la subarea validados GUARDANDO...");
        SubArea subArea = SubArea.builder()
                        .description(dto.getDescription())
                        .area(area)
                        .build();
        log.info("---> finalizado servicio crear subarea");
        return Utils.mapSubareaToDto(subAreaRepository.save(subArea));
    }

    @Override
    public SubAreaResponseDTO getById(Long id) {
        log.info("---> inicio servicio buscar subarea por id {}", id);
        log.info("---> buscando subarea...");
        SubArea subArea = subAreaRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra una subarea con id {}", id);
            return new NotFoundException("no se encuentra una subarea con id " + id);
        });
        log.info("---> finalizado el servicio buscar subarea por id");
        return Utils.mapSubareaToDto(subArea);
    }

    @Override
    public List<SubAreaResponseDTO> getAll() {
        log.info("---> inicio servicio buscar todas las subareas");
        log.info("---> buscando subareas...");
        List<SubArea> subAreas = subAreaRepository.findAll();
        if (subAreas.isEmpty()){
            log.error("ERROR: no hay subareas para mostrar");
            throw new NoContentException("no hay subareas para mostrar");
        }
        log.info("---> finalizado servicio buscar subareas");
        return subAreas.stream().map(Utils::mapSubareaToDto).collect(Collectors.toList());
    }

    @Override
    public SubAreaResponseDTO update(Long id, SubAreaRequestDTO dto) {
        log.info("---> inicio servicio actualizar subarea con id {}", id);
        log.info("---> validando...");
        if (dto.getDescription().isEmpty()){
            log.error("ERROR: no se envia descripcion nada para modificar");
            throw new BadRequestException("no se envia descripcion nada para modificar");
        }
        SubArea subareaVieja = subAreaRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra una subarea con id {}", id);
            return new NotFoundException("no se encuentra una subarea con id " + id);
        });
        if (subAreaRepository.existsByIdAndDescription(id, dto.getDescription())){
            log.error("ya existe esta subarea en esta area");
            throw new BadRequestException("ya existe esta subarea en esta area");
        }
        log.info("---> datos de area validados GUARDANDO CAMBIOS....");
        SubArea subArea = SubArea.builder()
                        .id(id)
                        .description(dto.getDescription())
                        .area(subareaVieja.getArea())
                        .build();
        log.info("---> finalizado el servicio actualizar subarea");
        return Utils.mapSubareaToDto(subAreaRepository.save(subArea));
    }
}
