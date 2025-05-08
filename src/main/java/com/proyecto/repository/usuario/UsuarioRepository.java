package com.proyecto.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.proyecto.model.usuario.UsuarioVO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioVO, Integer> {

    UsuarioVO findByNombreUsuario(String nombreUsuario);
  
}
