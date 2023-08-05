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
@Table(name = "EDITORIAL")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(length = 1)
    private String state;
}
