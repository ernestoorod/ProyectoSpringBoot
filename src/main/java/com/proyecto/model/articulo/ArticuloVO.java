package com.proyecto.model.articulo;

import java.math.BigDecimal;
import java.sql.Blob;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "articulos")
public class ArticuloVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private int stock;

    private boolean activo;

    private Blob imagen;

}

