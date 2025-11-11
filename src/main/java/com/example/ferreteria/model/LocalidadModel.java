package com.example.ferreteria.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "localidades")

public class LocalidadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @OneToMany(mappedBy = "localidad")
    private List<ClienteModel> clientes = new ArrayList<>();

    public LocalidadModel() {
    }

    public LocalidadModel(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
