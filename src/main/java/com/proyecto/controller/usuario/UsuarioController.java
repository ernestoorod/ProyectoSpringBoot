package com.proyecto.controller.usuario;

import com.proyecto.model.usuario.UsuarioVO;
import com.proyecto.service.usuario.UsuarioService;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioVO());
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") UsuarioVO usuario) {
        usuarioService.crear(usuario);
        return "redirect:/usuario/login";
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new UsuarioVO());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("usuario") UsuarioVO usuarioForm,
                        HttpSession session,
                        Model model) {
        UsuarioVO usuario = usuarioService.autenticar(usuarioForm.getNombreUsuario(), usuarioForm.getContrasena());
        if (usuario != null) {
            session.setAttribute("usuarioLogueado", usuario);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";
        }
    }

    @GetMapping("/perfil")
    public String verPerfil(HttpSession session, Model model) {
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    @GetMapping("/editar")
    public String editarPerfil(HttpSession session, Model model) {
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioLogueado");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }
        model.addAttribute("usuario", usuario);
        return "editar";
    }

    @PostMapping("/actualizar")
    public String actualizarPerfil(@ModelAttribute("usuario") UsuarioVO usuarioForm,
                                HttpSession session) {

        // 1) Obtenemos el usuario original de la sesión
        UsuarioVO sesion = (UsuarioVO) session.getAttribute("usuarioLogueado");
        if (sesion != null) {

            // 2) Ajustamos el ID y aplicamos la lógica de actualización
            usuarioForm.setId(sesion.getId());
            usuarioService.actualizar(usuarioForm);

            // 3) Recargamos desde BD el VO completo
            UsuarioVO actualizado = usuarioService.obtenerPorId(sesion.getId());

            // 4) Lo guardamos en la sesión para que tenga rol, contraseña, etc.
            session.setAttribute("usuarioLogueado", actualizado);
        }
        
        // 5) Redirigimos al perfil, donde los if de roles funcionarán
        return "redirect:/usuario/perfil";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminarCuenta(@PathVariable Integer id, HttpSession session) {
        usuarioService.eliminar(id);
        session.invalidate();
        return "redirect:/usuario/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/cambiorol")
    public String mostrarCambiarRol(Model model) {
        List<UsuarioVO> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "cambiorol";
    }

    @PostMapping("/cambiorol")
    public String cambiarRol(@RequestParam("usuarioId") Integer id,
                        @RequestParam("nuevoRol") String nuevoRol) {
    usuarioService.cambiarRol(id, nuevoRol);
    return "redirect:/usuario/cambiorol";
    }


    @GetMapping("/checkUsername")
    @ResponseBody
    public Map<String, Boolean> checkUsername(@RequestParam("nombreUsuario") String nombreUsuario) {
        boolean exists = usuarioService.existeNombreUsuario(nombreUsuario);
        return Collections.singletonMap("exists", exists);
    }


}
