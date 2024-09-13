package com.digitalhouse.reservaturnos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String mostrarFormularioDeLogin() {
        return "login"; // Nombre de la plantilla de login
    }
}
