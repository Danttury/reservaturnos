package com.digitalhouse.reservaturnos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El nombre no debe tener más de 50 caracteres.")
    private String nombre;

    @Size(max = 50, message = "El apellido no debe tener más de 50 caracteres.")
    private String apellido;

    @NotBlank(message = "La matrícula no puede estar en blanco y debe ser única.")
    @Column(unique = true, nullable = false)
    private String matricula;

    public Odontologo() {
        super();
    }

    public Odontologo(Long id, String nombre, String apellido, String matricula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Odontologo [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", matricula=" + matricula + "]";
    }
}