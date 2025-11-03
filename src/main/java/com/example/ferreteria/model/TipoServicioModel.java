package com.example.ferreteria.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_servicios")
public class TipoServicioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @OneToMany(mappedBy = "tipoServicio")
    private List<ServicioModel> servicios = new ArrayList<>();

    public TipoServicioModel() {
    }

    public TipoServicioModel(Long id, String nombre, List<ServicioModel> servicios) {
        this.id = id;
        this.nombre = nombre;
        this.servicios = servicios;
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

    public List<ServicioModel> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioModel> servicios) {
        this.servicios = servicios;
    }
}
