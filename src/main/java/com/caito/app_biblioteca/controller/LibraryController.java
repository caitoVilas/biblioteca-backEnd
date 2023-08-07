package com.caito.app_biblioteca.controller;

import com.caito.app_biblioteca.model.dto.AreaRequestDTO;
import com.caito.app_biblioteca.model.dto.AreaResponseDTO;
import com.caito.app_biblioteca.model.dto.LibrarieRequestDTO;
import com.caito.app_biblioteca.model.dto.LibraryResponseDTO;
import com.caito.app_biblioteca.service.contract.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@RestController
@RequestMapping("/api/v1/biblioteca/libraries")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Librerias")
public class LibraryController {
    private final LibraryService libraryService;

    @PostMapping
    @Operation(description = "servicio para crear librerias", summary = "servicio para crear librerias")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<LibraryResponseDTO> create(@RequestBody LibrarieRequestDTO dto){
        log.info("####  endpoint creacion de librerias");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "servicio que devuelve una libreria por id",
            summary = "servicio que devuelve una libreria por id si existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<LibraryResponseDTO> getOne(@PathVariable Long id){
        log.info("#### endpoint que busca un libreria por id");
        return ResponseEntity.ok(libraryService.getById(id));
    }

    @GetMapping()
    @Operation(description = "servicio que muestra todas las librerias",
            summary = "servicio que muestra todas las librerias")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<LibraryResponseDTO>> getAll(){
        log.info("####  endpoint que muestra todas las librerias");
        return ResponseEntity.ok(libraryService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(description = "servicio que modifica las librerias",
            summary = "servicio que modifica las librerias")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<LibraryResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody LibrarieRequestDTO dto){
        log.info("####  endpoint para modificar librerias");
        return ResponseEntity.ok(libraryService.update(id, dto));
    }
}
