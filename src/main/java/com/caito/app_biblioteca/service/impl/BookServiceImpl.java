package com.caito.app_biblioteca.service.impl;

import com.caito.app_biblioteca.exception.custom.BadRequestException;
import com.caito.app_biblioteca.exception.custom.NoContentException;
import com.caito.app_biblioteca.exception.custom.NotFoundException;
import com.caito.app_biblioteca.model.dto.BookRequestDTO;
import com.caito.app_biblioteca.model.dto.BookResponseDTO;
import com.caito.app_biblioteca.model.entity.Book;
import com.caito.app_biblioteca.model.entity.Editorial;
import com.caito.app_biblioteca.model.entity.SubArea;
import com.caito.app_biblioteca.repository.BookRepository;
import com.caito.app_biblioteca.repository.EditorialRepository;
import com.caito.app_biblioteca.repository.SubAreaRepository;
import com.caito.app_biblioteca.service.contract.BookService;
import com.caito.app_biblioteca.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final EditorialRepository editorialRepository;
    private final SubAreaRepository subAreaRepository;

    @Override
    public BookResponseDTO create(BookRequestDTO dto) {
        log.info("---> inicio servicio crear libro");
        log.info("---> validando...");
        if (dto.getTitle().isEmpty()){
            log.error("ERROR: el nombre para el libro es requerido");
            throw new BadRequestException("el nombre para el libro es requerido");
        }
        if (dto.getIsbn().isEmpty()){
            log.error("ERROR: el isbn es requerido");
            throw new BadRequestException("el isbn es requerido");
        }
        if (dto.getNumberPages().isEmpty()){
            log.error("ERROR: el numero de paginas es requerido");
            throw new BadRequestException("el numero de paginas es requerido");
        }
        if (dto.getYearPublicate().isEmpty()){
            log.error("ERROR: el año de publicacion es requerido");
            throw new BadRequestException("el año de publicacion es requerido");
        }
        Editorial editorial = editorialRepository.findById(dto.getIdEditorial()).orElseThrow(()->{
            log.error("ERROR: no se encuentra editorial con id {}", dto.getIdEditorial());
            return new BadRequestException("no se encuentra editorial con id " + dto.getIdEditorial());
        });
        SubArea subArea = subAreaRepository.findById(dto.getSubArea()).orElseThrow(()->{
            log.error("ERROR: no se encuentra subarea con id {}",dto.getSubArea());
            return new BadRequestException("no se encuentra subarea con id " + dto.getSubArea());
        });
        if (bookRepository.existsByIsbn(dto.getIsbn())){
            log.error("ERROR: ya existe un libro con isbn {}", dto.getIsbn());
            throw new BadRequestException("ya existe un libro con isbn " + dto.getIsbn());
        }
        log.info("---> datos de libro validados GUARDANDO...");
        Book book = Book.builder()
                        .title(dto.getTitle())
                        .subtitle(dto.getSubtitle())
                        .isbn(dto.getIsbn())
                        .description(dto.getDescription())
                        .numberPages(dto.getNumberPages())
                        .yearPublicate(dto.getYearPublicate())
                        .editorial(editorial)
                        .subArea(subArea)
                        .state(true)
                        .build();
        log.info("---> finalizado servicio crear libro");
        return Utils.mapBookToDto(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO getById(Long id) {
        log.info("---> inicio servicio buscar libro por id {}", id);
        log.info("---> buscando libro...");
        Book book = bookRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra un libro con id {}", id);
            return new NotFoundException("no se encuentra un libro con id " + id);
        });
        log.info("---> finalizado el servicio buscar libro por id");
        return Utils.mapBookToDto(book);
    }

    @Override
    public List<BookResponseDTO> getAll() {
        log.info("---> inicio servicio buscar todos los libros");
        log.info("---> buscando libros...");
        List<Book> libros = bookRepository.findAll();
        if (libros.isEmpty()){
            log.error("ERROR: no hay libros para mostrar");
            throw new NoContentException("no hay libros para mostrar");
        }
        log.info("---> finalizado servicio buscar libros");
        return libros.stream().map(Utils::mapBookToDto).collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO update(Long id, BookRequestDTO dto) {
        log.info("---> inicio servicio actualizar libro con id {}", id);
        log.info("---> validando...");
        Book oldBook = bookRepository.findById(id).orElseThrow(()->{
            log.error("ERROR: no se encuentra un libro con id {}", id);
            return new NotFoundException("no se encuentra un libro con id " + id);
        });
       if (!dto.getIsbn().isEmpty()){
           Book otroLibro = bookRepository.searchIsbn(id, dto.getIsbn());
           if (otroLibro != null){
               log.error("ERROR: existe otro libro con isbn {}", dto.getIsbn());
               throw new BadRequestException("existe otro libro con isbn " + dto.getIsbn());
           }
       }
       Editorial editorial = null;
       SubArea subArea = null;
       if (dto.getIdEditorial() != null){
            editorial = editorialRepository.findById(dto.getIdEditorial()).orElseThrow(()->{
               log.error("ERROR: no se encuentra editorial con id {}", dto.getIdEditorial());
                return new BadRequestException("no se encuentra editorial con id " + dto.getIdEditorial());
           });
       }
       if (dto.getSubArea() != null){
            subArea = subAreaRepository.findById(dto.getSubArea()).orElseThrow(()->{
               log.error("ERROR: no se encuentra subarea con id {}", dto.getSubArea());
                return new BadRequestException("no se encuentra subarea con id " + dto.getSubArea());
           });
       }
        log.info("---> datos de libreria validados GUARDANDO CAMBIOS....");
        Book book =Book.builder()
                        .id(id)
                        .title(dto.getTitle().isEmpty()? oldBook.getTitle(): dto.getTitle())
                        .subtitle(dto.getSubtitle().isEmpty()? oldBook.getSubtitle() : dto.getSubtitle())
                        .isbn(dto.getIsbn().isEmpty()? oldBook.getIsbn(): dto.getIsbn())
                        .description(dto.getDescription().isEmpty()? oldBook.getDescription(): dto.getDescription())
                        .numberPages(dto.getNumberPages().isEmpty()? oldBook.getNumberPages() : dto.getNumberPages())
                        .yearPublicate(dto.getYearPublicate().isEmpty()? oldBook.getYearPublicate() : dto.getYearPublicate())
                        .state(oldBook.isState())
                        .editorial(dto.getIdEditorial() == null? oldBook.getEditorial(): editorial)
                        .subArea(dto.getSubArea() == null? oldBook.getSubArea(): subArea)
                        .build();
        log.info("---> finalizado el servicio actualizar libro");
        return Utils.mapBookToDto(bookRepository.save(book));
    }
}
