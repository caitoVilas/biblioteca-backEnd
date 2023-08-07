package com.caito.app_biblioteca.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "modelo que representa un area para crear/modificar")
public class AreaRequestDTO {
    @Schema(name = "description", required = true, example = "bases de datos")
    private String description;
}
