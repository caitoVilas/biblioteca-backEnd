package com.caito.app_biblioteca.util;

import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;
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
}
