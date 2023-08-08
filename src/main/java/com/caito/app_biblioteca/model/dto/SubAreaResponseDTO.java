package com.caito.app_biblioteca.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author claudio.vilas
 * date: 08/2023
 * */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "modelo que representa una subarea paralas respuestas")
public class SubAreaResponseDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "description")
    private String description;
    @Schema(name = "state")
    private String state;
    @Schema(name = "area")
    private AreaResponseDTO area;
}
