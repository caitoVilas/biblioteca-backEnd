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
@Schema(description = "modelo que representa una biblioteca para las respuestas")
public class LibraryResponseDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "name")
    private String name;
    @Schema(name = "description")
    private String descripotion;
    @Schema(name = "address")
    private String address;
    @Schema(name = "email")
    private String email;
    @Schema(name = "tel")
    private String tel;
    @Schema(name = "status")
    private boolean staus;
}
