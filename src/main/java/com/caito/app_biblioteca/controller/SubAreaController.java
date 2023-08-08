package com.caito.app_biblioteca.controller;

import com.caito.app_biblioteca.model.dto.SubAreaRequestDTO;
import com.caito.app_biblioteca.model.dto.SubAreaResponseDTO;
import com.caito.app_biblioteca.service.contract.SubAreaService;
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
@RequestMapping("/api/v1/biblioteca/subareas")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Subareas")
public class SubAreaController {
    private final SubAreaService subAreaService;

    @PostMapping
    @Operation(description = "servicio para crear subareas", summary = "servicio para crear subareas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<SubAreaResponseDTO> create(@RequestBody SubAreaRequestDTO dto){
        log.info("####  endpoint creacion de subareas");
        return ResponseEntity.status(HttpStatus.CREATED).body(subAreaService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "servicio que devuelve una subarea por id",
            summary = "servicio que devuelve una subarea por id si existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<SubAreaResponseDTO> getOne(@PathVariable Long id){
        log.info("#### endpoint que busca una subarea por id");
        return ResponseEntity.ok(subAreaService.getById(id));
    }

    @GetMapping()
    @Operation(description = "servicio que muestra todas las subareas",
            summary = "servicio que muestra todas las subareas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<SubAreaResponseDTO>> getAll(){
        log.info("####  endpoint que muestra todas las subareas");
        return ResponseEntity.ok(subAreaService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(description = "servicio que modifica las subareas",
            summary = "servicio que modifica subareas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<SubAreaResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody SubAreaRequestDTO dto){
        log.info("####  endpoint para modificar subareas");
        return ResponseEntity.ok(subAreaService.update(id, dto));
    }
}
