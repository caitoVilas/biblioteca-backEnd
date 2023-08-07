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
@Schema(description = "modelo que representa un libro para las respuestas")
public class BookResponseDTO {
    @Schema(name = "id")
    private Long id;
    @Schema(name = "title")
    private String title;
    @Schema(name = "subtitle")
    private String subtitle;
    @Schema(name = "isbn")
    private String isbn;
    @Schema(name = "description")
    private String  description;
    @Schema(name = "numberPages")
    private String numberPages;
    @Schema(name = "yearPublicate")
    private String yearPublicate;
    @Schema(name = "state")
    private String state;
    @Schema(name = "editorial")
    private EditorialResponseDTO editorial;
    @Schema(name = "subarea")
    private SubAreaResponseDTO subArea;
}
