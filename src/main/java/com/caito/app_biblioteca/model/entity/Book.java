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
@Table(name = "BOOKS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 70)
    private String title;
    @Column(length = 70)
    private String subtitle;
    @Column(length = 20)
    private String isbn;
    private String  description;
    @Column(length = 10)
    private String numberPages;
    @Column(length = 5)
    private String yearPublicate;
    private boolean state;
    @ManyToOne
    @JoinColumn(name = "idEditorial")
    private Editorial editorial;
    @ManyToOne
    @JoinColumn(name = "idSubarea")
    private SubArea subArea;



}
