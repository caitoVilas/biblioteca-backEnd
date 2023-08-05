package com.caito.app_biblioteca.controller;

import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;
import com.caito.app_biblioteca.service.contract.EditorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@RestController
@RequestMapping("/api/v1/biblioteca/editoriales")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Editoriales")
public class EditorialController {
    private final EditorialService editorialService;

    @PostMapping
    @Operation(description = "servicio para crear editoriales", summary = "servicio para crear editoriales")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<EditorialResponseDTO> create(@RequestBody EditorialRequestDTO dto){
        log.info("####  endpoint creacion de editoriales");
        return ResponseEntity.status(HttpStatus.CREATED).body(editorialService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "servicio que devuelve una editorial por id",
               summary = "servicio que devuelve una editorial por id si existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<EditorialResponseDTO> getOne(@PathVariable Long id){
        log.info("#### endpoint que busca editorial por id");
        return ResponseEntity.ok(editorialService.getById(id));
    }

    @GetMapping()
    @Operation(description = "servivio que muestra todas las editoriales",
               summary = "servivio que muestra todas las editoriales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<EditorialResponseDTO>> getAll(){
        log.info("####  endpoint que muestra todas las editoriales");
        return ResponseEntity.ok(editorialService.getAll());
    }
}
