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
    @Schema(name = "description", required = true, example = "Bases de Datos")
    private String description;
    @Schema(name = "idArea", required = true, example = "1")
    private Long idArea;

}
