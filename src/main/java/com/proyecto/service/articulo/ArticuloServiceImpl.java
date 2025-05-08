package com.proyecto.service.articulo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.articulo.ArticuloVO;
import com.proyecto.repository.articulo.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public void crear(ArticuloVO articulo) {
        articuloRepository.save(articulo);
    }

    @Override
    public void actualizar(ArticuloVO articulo) {
        articuloRepository.save(articulo);
    }

    @Override
    public void eliminar(Integer id) {
        articuloRepository.deleteById(id);
    }

    @Override
    public ArticuloVO obtenerPorId(Integer id) {
        return articuloRepository.findById(id).orElse(null);
    }

    @Override
    public List<ArticuloVO> listarArticulos() {
        return articuloRepository.findAll();
    }  

}
