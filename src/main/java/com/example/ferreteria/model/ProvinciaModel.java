package com.example.ferreteria.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provincias")

public class ProvinciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @OneToMany(mappedBy = "provincia")
    private List<ClienteModel> clientes = new ArrayList<>();

    public ProvinciaModel() {
    }

    public ProvinciaModel(String nombre) {
        this.nombre = nombre;
    }

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
}

