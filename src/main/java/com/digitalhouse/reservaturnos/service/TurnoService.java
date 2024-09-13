package com.digitalhouse.reservaturnos.service;

import com.digitalhouse.reservaturnos.model.Turno;
import com.digitalhouse.reservaturnos.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> obtenerTurnoPorId(Long id) {
        return turnoRepository.findById(id);
    }

    public Turno registrarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}
