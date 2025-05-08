package com.proyecto.service.usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto.model.usuario.UsuarioVO;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public UsuarioVO crear(UsuarioVO usuarioVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crear'");
    }

    @Override
    public UsuarioVO actualizar(UsuarioVO usuarioVO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public void eliminar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public UsuarioVO obtenerPorId(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPorId'");
    }

    @Override
    public List<UsuarioVO> listarUsuarios() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarUsuarios'");
    }

    @Override
    public UsuarioVO autenticar(String nombreUsuario, String contrasena) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'autenticar'");
    }

}
