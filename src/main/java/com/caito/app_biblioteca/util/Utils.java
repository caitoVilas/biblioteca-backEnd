package com.caito.app_biblioteca.util;

import com.caito.app_biblioteca.model.dto.*;
import com.caito.app_biblioteca.model.entity.*;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

public class Utils {

    public static EditorialResponseDTO mapEditorialToDto(Editorial editorial){
        return EditorialResponseDTO.builder()
                .id(editorial.getId())
                .name(editorial.getName())
                .state(editorial.isState()?"A" : "I")
                .build();
    }
    public static Editorial mapEditorialToEntity(EditorialRequestDTO dto){
        return Editorial.builder()
                .name(dto.getName())
                .build();
    }

    public static AreaResponseDTO mapAreaToDTO(Area area){
        return AreaResponseDTO.builder()
                .id(area.getId())
                .description(area.getDescription())
                .status(area.isStatus()?"A": "I")
                .build();
    }

    public static Area mapAreaToEntity(AreaRequestDTO dto){
        return Area.builder()
                .description(dto.getDescription())
                .build();
    }

    public static LibraryResponseDTO mapLibraryToDto(Library library){
        return LibraryResponseDTO.builder()
                .id(library.getId())
                .name(library.getName())
                .description(library.getDescripotion())
                .address(library.getAddress())
                .email(library.getEmail())
                .tel(library.getTel())
                .staus(library.isStaus()?"A": "I")
                .build();
    }

    public static Library mapToLibraryEntity(LibrarieRequestDTO dto){
        return Library.builder()
                .name(dto.getName())
                .descripotion(dto.getDescription())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .tel(dto.getTel())
                .build();
    }

    public static SubAreaResponseDTO mapSubareaToDto(SubArea subArea){
        return SubAreaResponseDTO.builder()
                .id(subArea.getId())
                .descripcion(subArea.getDescription())
                .area(mapAreaToDTO(subArea.getArea()))
                .build();
    }

    public static BookResponseDTO mapBookToDto(Book book){
        return BookResponseDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .numberPages(book.getNumberPages())
                .yearPublicate(book.getYearPublicate())
                .state(book.isState()? "A": "I")
                .editorial(mapEditorialToDto(book.getEditorial()))
                .subArea(mapSubareaToDto(book.getSubArea()))
                .build();
    }
}
