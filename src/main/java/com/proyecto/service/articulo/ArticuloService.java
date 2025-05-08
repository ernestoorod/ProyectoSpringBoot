package com.proyecto.service.articulo;

import java.util.List;

import com.proyecto.model.articulo.ArticuloVO;

/**
 * Servicio para la gestión de artículos.
 * Define las operaciones principales relacionadas con el manejo de artículos en el sistema.
 */
public interface ArticuloService {

    /**
     * Crea un nuevo artículo en el sistema.
     *
     * @param articuloVO Objeto que contiene la información del artículo a crear.
     * @return El artículo creado con su ID generado.
     */
    public ArticuloVO crear(ArticuloVO articuloVO);

    /**
     * Actualiza la información de un artículo existente.
     *
     * @param articuloVO Objeto con los datos actualizados del artículo.
     * @return El artículo actualizado.
     */
    public ArticuloVO actualizar(ArticuloVO articuloVO);

    /**
     * Elimina un artículo del sistema según su ID.
     *
     * @param id ID del artículo a eliminar.
     */
    public void eliminar(Integer id);

    /**
     * Obtiene un artículo por su ID.
     *
     * @param id ID del artículo.
     * @return El artículo correspondiente al ID proporcionado.
     */
    public ArticuloVO obtenerPorId(Integer id);

    /**
     * Lista todos los artículos registrados en el sistema.
     *
     * @return Lista de artículos.
     */
    public List<ArticuloVO> listarArticulos();
    
}
