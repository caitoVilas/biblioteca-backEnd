package com.caito.app_biblioteca.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.Column;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Schema(description = "modelo que representa una libreria para crear/modificar")
public class LibrarieRequestDTO {
    @Schema(name = "name", required = true, example = "Biblioteca Sarmiento")
    private String name;
    @Schema(name = "description", required = false, example = "biblioteca general para todo publico")
    private String descripotion;
    @Schema(name = "address", required = true, example = "Peron 2345, V.Alsina")
    private String address;
    @Schema(name = "email", required = true, example = "sarmiento@sarmiento.com.ar")
    private String email;
    @Schema(name = "tel", required = false, example = "42090815")
    private String tel;
}
