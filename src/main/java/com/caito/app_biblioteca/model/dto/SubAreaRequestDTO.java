package com.caito.app_biblioteca.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author claudio.vilas
 * date. 08/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "modelo que representa una subarea para crear/modiicar")
public class SubAreaRequestDTO {
    @Schema(name = "id", required = true, example = "Bases de Datos")
    private String description;
    private Long idArea;

}
