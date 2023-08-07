package com.caito.app_biblioteca.controller;

import com.caito.app_biblioteca.model.dto.AreaRequestDTO;
import com.caito.app_biblioteca.model.dto.AreaResponseDTO;
import com.caito.app_biblioteca.model.dto.EditorialRequestDTO;
import com.caito.app_biblioteca.model.dto.EditorialResponseDTO;
import com.caito.app_biblioteca.service.contract.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Controller
@RequestMapping("/api/v1/biblioteca/areas")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Areas")
public class AreaController {
    private final AreaService areaService;

    @PostMapping
    @Operation(description = "servicio para crear areas", summary = "servicio para crear areas")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<AreaResponseDTO> create(@RequestBody AreaRequestDTO dto){
        log.info("####  endpoint creacion de areas");
        return ResponseEntity.status(HttpStatus.CREATED).body(areaService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "servicio que devuelve una area por id",
            summary = "servicio que devuelve una area por id si existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<AreaResponseDTO> getOne(@PathVariable Long id){
        log.info("#### endpoint que busca un area por id");
        return ResponseEntity.ok(areaService.getById(id));
    }

    @GetMapping()
    @Operation(description = "servicio que muestra todas las areas",
            summary = "servicio que muestra todas las areas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<AreaResponseDTO>> getAll(){
        log.info("####  endpoint que muestra todas las areas");
        return ResponseEntity.ok(areaService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(description = "servicio que modifica las areas",
            summary = "servicio que modifica areas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<AreaResponseDTO> update(@PathVariable Long id,
                                                       @RequestBody AreaRequestDTO dto){
        log.info("####  endpoint para modificar areas");
        return ResponseEntity.ok(areaService.update(id, dto));
    }
}
