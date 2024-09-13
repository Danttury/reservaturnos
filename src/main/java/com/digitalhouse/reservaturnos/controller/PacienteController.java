package com.digitalhouse.reservaturnos.controller;

import com.digitalhouse.reservaturnos.model.Paciente;
import com.digitalhouse.reservaturnos.service.PacienteService;
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
@RequestMapping ("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "pacientes/list";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String mostrarFormularioDeNuevoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "pacientes/form";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String guardarPaciente(@Valid Paciente paciente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("paciente", paciente);
            return "pacientes/form";
        }
        pacienteService.guardarPaciente(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Paciente paciente = pacienteService.obtenerPacientePorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de paciente inválido:" + id));
        model.addAttribute("paciente", paciente);
        return "pacientes/form";
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String actualizarPaciente(@Valid Paciente paciente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pacientes/edit";
        }
        pacienteService.actualizarPaciente(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String eliminarPaciente(@PathVariable("id") Long id) {
        pacienteService.eliminarPaciente(id);
        return "redirect:/pacientes";
    }
}
