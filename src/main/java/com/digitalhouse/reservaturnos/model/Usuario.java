package com.digitalhouse.reservaturnos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de usuario no puede estar en blanco.")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "La contraseña no puede estar en blanco.")
    private String password;

    @NotNull(message = "El rol no puede ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    // Constructor sin argumentos
    public Usuario() {
        super();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
