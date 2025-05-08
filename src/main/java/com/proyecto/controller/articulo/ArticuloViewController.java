package com.proyecto.controller.articulo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticuloViewController {
    @GetMapping("/articulos")
    public String verArticulos() {
        return "articulos";
    }
}