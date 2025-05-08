package com.proyecto.model.usuario;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class UsuarioVO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;

    private String nombre;

    private String apellidos;

    private String nombreUsuario;

    private String contrasena;

    private String email;

    private String telefono;

    private String rol;

}
