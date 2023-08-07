package com.caito.app_biblioteca.util;

import com.caito.app_biblioteca.model.dto.AreaRequestDTO;
import com.caito.app_biblioteca.model.dto.AreaResponseDTO;
import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;
import com.caito.app_biblioteca.model.entity.Area;
import com.caito.app_biblioteca.model.entity.Editorial;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

public class Utils {

    public static EditorialResponseDTO mapEditorialToDto(Editorial editorial){
        return EditorialResponseDTO.builder()
                .id(editorial.getId())
                .name(editorial.getName())
                .state(editorial.isState()?"A" : "I")
                .build();
    }
    public static Editorial mapEditorialToEntity(EditorialRequestDTO dto){
        return Editorial.builder()
                .name(dto.getName())
                .build();
    }

    public static AreaResponseDTO mapAreaToDTO(Area area){
        return AreaResponseDTO.builder()
                .id(area.getId())
                .description(area.getDescription())
                .status(area.isStatus()?"A": "I")
                .build();
    }

    public static Area mapAreaToEntity(AreaRequestDTO dto){
        return Area.builder()
                .description(dto.getDescription())
                .build();
    }
}
