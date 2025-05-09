package com.proyecto.service.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.model.usuario.UsuarioVO;
import com.proyecto.repository.usuario.UsuarioRepository;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void crear(UsuarioVO usuario) {
        String hashedPassword = BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt());
        usuario.setContrasena(hashedPassword);
        usuarioRepository.save(usuario);
    }

    @Override
    public void actualizar(UsuarioVO usuario) {
        UsuarioVO existente = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existente != null) {
            if (!BCrypt.checkpw(usuario.getContrasena(), existente.getContrasena())) {
                usuario.setContrasena(BCrypt.hashpw(usuario.getContrasena(), BCrypt.gensalt()));
            }
        }
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
        if (usuario != null && BCrypt.checkpw(contrasena, usuario.getContrasena())) {
            return usuario;
        }
        return null;
    }
}
