package com.proyecto.controller.usuario;

import com.proyecto.model.usuario.UsuarioVO;
import com.proyecto.service.usuario.UsuarioService;
import jakarta.servlet.http.HttpSession;
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
            return "redirect:/usuario/perfil";
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
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuarioLogueado");
        if (usuario != null) {
            usuarioForm.setId(usuario.getId());
            usuarioService.actualizar(usuarioForm);
            session.setAttribute("usuarioLogueado", usuarioForm);
        }
        return "redirect:/usuario/perfil";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCuenta(@PathVariable Integer id, HttpSession session) {
        usuarioService.eliminar(id);
        session.invalidate();
        return "redirect:/usuario/login";
    }
}
