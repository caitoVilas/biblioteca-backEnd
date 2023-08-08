package com.caito.app_biblioteca.controller;

import com.caito.app_biblioteca.model.dto.BookRequestDTO;
import com.caito.app_biblioteca.model.dto.BookResponseDTO;
import com.caito.app_biblioteca.model.dto.SubAreaRequestDTO;
import com.caito.app_biblioteca.model.dto.SubAreaResponseDTO;
import com.caito.app_biblioteca.service.contract.BookService;
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
@RequestMapping("/api/v1/biblioteca/libros")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Libros")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @Operation(description = "servicio para crear libros", summary = "servicio para crear libros")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookRequestDTO dto){
        log.info("####  endpoint creacion de libros");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(description = "servicio que devuelve un libro por id",
            summary = "servicio que devuelve un libro por id si existe")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<BookResponseDTO> getOne(@PathVariable Long id){
        log.info("#### endpoint que busca un libro por id");
        return ResponseEntity.ok(bookService.getById(id));
    }

    @GetMapping()
    @Operation(description = "servicio que muestra todos los libros",
            summary = "servicio que muestra todos los libros")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<List<BookResponseDTO>> getAll(){
        log.info("####  endpoint que muestra todos los libros");
        return ResponseEntity.ok(bookService.getAll());
    }

    @PutMapping("/{id}")
    @Operation(description = "servicio que modifica los libros",
            summary = "servicio que modifica libros")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "400", description = "bad request"),
            @ApiResponse(responseCode = "500", description = "internal server error")
    })
    public ResponseEntity<BookResponseDTO> update(@PathVariable Long id,
                                                     @RequestBody BookRequestDTO dto){
        log.info("####  endpoint para modificar libros");
        return ResponseEntity.ok(bookService.update(id, dto));
    }
}
