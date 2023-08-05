package com.caito.app_biblioteca.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorDTO {
    private int code;
    private LocalDateTime timestamp;
    private String message;
    private String url;
}
