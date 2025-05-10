package com.proyecto.controller.articulo;

import com.proyecto.service.articulo.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticuloViewController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/articulos")
    public String verArticulos(Model model) {
        model.addAttribute("articulos", articuloService.listarArticulos());
        return "articulos";
    }
}
