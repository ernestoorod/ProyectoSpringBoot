package com.proyecto.service.usuario;

import java.util.List;

import com.proyecto.model.usuario.UsuarioVO;

/**
 * Servicio para la gestión de usuarios.
 * Define las operaciones principales relacionadas con el manejo de usuarios del sistema.
 */
public interface UsuarioService {

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuarioVO Objeto que contiene la información del usuario a crear.
     * @return El usuario creado con su ID generado.
     */
    public void crear(UsuarioVO usuario);

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuarioVO Objeto con los datos actualizados del usuario.
     * @return El usuario actualizado.
     */
    public void actualizar(UsuarioVO usuario);

    /**
     * Elimina un usuario del sistema según su ID.
     *
     * @param id ID del usuario a eliminar.
     */
    public void eliminar(Integer id);

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return El usuario correspondiente al ID proporcionado.
     */
    public UsuarioVO obtenerPorId(Integer id);

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return Lista de usuarios.
     */
    public List<UsuarioVO> listarUsuarios();

    /**
     * Autentica a un usuario en base a su nombre de usuario y contraseña.
     *
     * @param nombreUsuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @return El usuario autenticado si las credenciales son válidas, de lo contrario null.
     */
    public UsuarioVO autenticar(String nombreUsuario, String contrasena);


    public void cambiarRol(Integer id, String nuevoRol);

    boolean existeNombreUsuario(String nombreUsuario);
}
