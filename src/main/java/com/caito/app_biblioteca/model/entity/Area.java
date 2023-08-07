package com.caito.app_biblioteca.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author claudio.vilas
 * date: 08/2023
 */

@Entity
@Table(name = "AREA")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean status;
}
