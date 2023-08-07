package com.caito.app_biblioteca.model.dto;

import com.caito.app_biblioteca.model.entity.Editorial;
import com.caito.app_biblioteca.model.entity.SubArea;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "modelo que representa un libro para crear modificar")
public class BookRequestDTO {
    @Schema(name = "title", required = true, example = "Diseño de bases de Datos")
    private String title;
    @Schema(name = "subtitle", required = false, example = "diseño de bases de datos relacional")
    private String subtitle;
    @Schema(name = "isbn", required = true, example = "0-7645-2641-3")
    private String isbn;
    @Schema(name = "description", required = false, example = "bases de datos")
    private String  description;
    @Schema(name = "numberPages", required = true, example = "458")
    private String numberPages;
    @Schema(name = "yearPublicate", required = true, example = "2015")
    private String yearPublicate;
    @Schema(name = "idEditorial",required = true, example = "1")
    private Long idEditorial;
    @Schema(name = "idSubarea", required = true, example = "1")
    private Long subArea;
}
