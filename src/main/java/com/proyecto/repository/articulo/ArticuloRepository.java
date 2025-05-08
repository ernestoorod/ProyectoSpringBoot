package com.proyecto.repository.articulo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.proyecto.model.articulo.ArticuloVO;

@Repository
public interface ArticuloRepository extends JpaRepository<ArticuloVO, Integer> {
    
}
