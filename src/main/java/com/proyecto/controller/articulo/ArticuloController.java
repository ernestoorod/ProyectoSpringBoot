package com.proyecto.controller.articulo;

import com.proyecto.model.articulo.ArticuloVO;
import com.proyecto.service.articulo.ArticuloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    // Listar todos los artículos
    @GetMapping
    public List<ArticuloVO> listar() {
        return articuloService.listarArticulos();
    }

    // Crear nuevo artículo
    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody ArticuloVO articulo) {
        articuloService.crear(articulo);
        return ResponseEntity.ok().build();
    }

    // Obtener artículo por Id
    @GetMapping("/{id}")
    public ResponseEntity<ArticuloVO> obtener(@PathVariable Integer id) {
        ArticuloVO articulo = articuloService.obtenerPorId(id);
        if (articulo != null) {
            return ResponseEntity.ok(articulo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar artículo
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody ArticuloVO articuloVO) {
        ArticuloVO existente = articuloService.obtenerPorId(id);
        if (existente != null) {
            articuloVO.setId(id); // Asegúrate de que el ID esté correcto
            articuloService.actualizar(articuloVO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ArticuloVO existente = articuloService.obtenerPorId(id);
        if (existente != null) {
            articuloService.eliminar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
