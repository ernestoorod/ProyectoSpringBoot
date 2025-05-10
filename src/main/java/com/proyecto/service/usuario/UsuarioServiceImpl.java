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
    public void actualizar(UsuarioVO usuarioForm) {
        // 1) Recupera el usuario existente
        UsuarioVO existente = usuarioRepository.findById(usuarioForm.getId())
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // 2) Campos obligatorios que siempre se actualizan
        existente.setNombre(usuarioForm.getNombre());
        existente.setApellidos(usuarioForm.getApellidos());
        existente.setNombreUsuario(usuarioForm.getNombreUsuario());
        existente.setEmail(usuarioForm.getEmail());
        existente.setTelefono(usuarioForm.getTelefono());

        // 3) Rol: solo cambiamos si nos han enviado uno nuevo
        String nuevoRol = usuarioForm.getRol();
        if (nuevoRol != null && !nuevoRol.isBlank()) {
            existente.setRol(nuevoRol);
        }

        // 4) Contraseña: igual, solo rehaseamos si han puesto una distinta
        String nuevaPass = usuarioForm.getContrasena();
        if (nuevaPass != null && !nuevaPass.isBlank()) {
            if (!BCrypt.checkpw(nuevaPass, existente.getContrasena())) {
                String hash = BCrypt.hashpw(nuevaPass, BCrypt.gensalt());
                existente.setContrasena(hash);
            }
        }

        // 5) Persistimos cambios
        usuarioRepository.save(existente);
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

    @Override
    public void cambiarRol(Integer id, String nuevoRol) {
        UsuarioVO u = usuarioRepository.findById(id).orElseThrow();
        u.setRol(nuevoRol);
        usuarioRepository.save(u);
    }

}
