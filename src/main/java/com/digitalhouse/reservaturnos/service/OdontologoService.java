package com.digitalhouse.reservaturnos.service;

import com.digitalhouse.reservaturnos.model.Odontologo;
import com.digitalhouse.reservaturnos.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> obtenerOdontologoPorId(Long id) {
        return odontologoRepository.findById(id);
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    public Optional<Odontologo> obtenerOdontologoPorMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }

    public void actualizarOdontologo(Odontologo odontologo) {
        Optional<Odontologo> odontologoExistente = odontologoRepository.findById(odontologo.getId());
        if (odontologoExistente.isPresent()) {
            Odontologo odontologoActualizado = odontologoExistente.get();
            odontologoActualizado.setNombre(odontologo.getNombre());
            odontologoActualizado.setApellido(odontologo.getApellido());
            odontologoRepository.save(odontologoActualizado);
        }
    }
}