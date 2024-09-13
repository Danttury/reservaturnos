package com.digitalhouse.reservaturnos.controller;

import com.digitalhouse.reservaturnos.model.Odontologo;
import com.digitalhouse.reservaturnos.service.OdontologoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listarOdontologos(Model model) {
        model.addAttribute("odontologos", odontologoService.listarOdontologos());
        return "odontologos/list";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String mostrarFormularioDeNuevoOdontologo(Model model) {
        model.addAttribute("odontologo", new Odontologo());
        return "odontologos/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String guardarOdontologo(@Valid Odontologo odontologo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("odontologo", odontologo);
            return "odontologos/form";
        }
        odontologoService.guardarOdontologo(odontologo);
        return "redirect:/odontologos";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Odontologo odontologo = odontologoService.obtenerOdontologoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de odontólogo inválido:" + id));
        model.addAttribute("odontologo", odontologo);
        return "odontologos/edit";
    }


    @PostMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String actualizarOdontologo(@Valid Odontologo odontologo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "odontologos/edit";
        }
        odontologoService.actualizarOdontologo(odontologo);
        return "redirect:/odontologos";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String eliminarOdontologo(@PathVariable("id") Long id) {
        odontologoService.eliminarOdontologo(id);
        return "redirect:/odontologos";
    }
}
