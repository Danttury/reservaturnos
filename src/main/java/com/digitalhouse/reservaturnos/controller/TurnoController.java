package com.digitalhouse.reservaturnos.controller;

import com.digitalhouse.reservaturnos.model.Turno;
import com.digitalhouse.reservaturnos.service.OdontologoService;
import com.digitalhouse.reservaturnos.service.PacienteService;
import com.digitalhouse.reservaturnos.service.TurnoService;
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
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String listarTurnos(Model model) {
        model.addAttribute("turnos", turnoService.listarTurnos());
        return "turnos/list";
    }

    @GetMapping("/nuevo")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String mostrarFormularioDeNuevoTurno(Model model) {
        model.addAttribute("turno", new Turno());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("odontologos", odontologoService.listarOdontologos());
        return "turnos/form";
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String registrarTurno(@Valid Turno turno, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("turno", turno);
            return "turnos/form";
        }
        turnoService.registrarTurno(turno);
        return "redirect:/turnos";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public String mostrarFormularioDeEdicion(@PathVariable("id") Long id, Model model) {
        Turno turno = turnoService.obtenerTurnoPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de turno inválido:" + id));
        model.addAttribute("turno", turno);
        return "turnos/form";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String eliminarTurno(@PathVariable("id") Long id) {
        turnoService.eliminarTurno(id);
        return "redirect:/turnos";
    }
}
