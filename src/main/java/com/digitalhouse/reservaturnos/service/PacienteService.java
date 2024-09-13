package com.digitalhouse.reservaturnos.service;

import com.digitalhouse.reservaturnos.model.Paciente;
import com.digitalhouse.reservaturnos.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> obtenerPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    // Actualizar un paciente existente
    public void actualizarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findById(paciente.getId());
        if (pacienteExistente.isPresent()) {
            Paciente pacienteActualizado = pacienteExistente.get();
            pacienteActualizado.setNombre(paciente.getNombre());
            pacienteActualizado.setApellido(paciente.getApellido());
            pacienteActualizado.setDomicilio(paciente.getDomicilio());
            pacienteActualizado.setFechaAlta(paciente.getFechaAlta());
            pacienteRepository.save(pacienteActualizado);
        }
    }
}
