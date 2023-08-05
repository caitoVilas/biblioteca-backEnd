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
@Schema(description = "modelo que representa una editorial para las respuesas")
public class EditorialResponseDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "name")
    private String name;
    @Schema(name = "state")
    private String state;
}
