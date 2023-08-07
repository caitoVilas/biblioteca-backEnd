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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "modelo que representa un area para las respuestas")
public class AreaResponseDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "description")
    private String description;
    @Schema(name = "status")
    private String status;
}
