package com.proyecto.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.usuario.UsuarioVO;
import com.proyecto.repository.usuario.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void crear(UsuarioVO usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizar(UsuarioVO usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioVO obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<UsuarioVO> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioVO autenticar(String nombreUsuario, String contrasena) {
        UsuarioVO usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

}
