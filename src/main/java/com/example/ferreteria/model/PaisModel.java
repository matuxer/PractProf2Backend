package com.example.ferreteria.model;

import jakarta.persistence.*;


@Entity
@Table(name="paises")
public class PaisModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nombre", length = 50 )
    private String nombre;

    public PaisModel() {
    }

    public PaisModel(Long id, String nombre) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
