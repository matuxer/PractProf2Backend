package com.example.ferreteria.model;

import jakarta.persistence.*;


@Entity
@Table(name = "tipos servicios")
public class TipoServicioModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50 )
    private String Nombre;

    public TipoServicioModel() {
    }

    public TipoServicioModel(Long id, String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
