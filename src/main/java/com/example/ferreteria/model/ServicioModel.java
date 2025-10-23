package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "servicio")
public class ServicioModel {

    //id: INT AUTOINC UNIQUE
    //precio: FLOAT
    //tipo_id: INT → FOREIGNKEY → Servicio_Tipo
    //id_especialista: INT → FOREIGNKEY → Especialista

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id; 

    @Column(name="precio")
    private float precio;

    @Column(name="tipo_id")
    private int tipo_id;

    @Column(name="id_especialista")
    private int id_especialista;

    public ServicioModel() {
    }

    public ServicioModel(Long id, float precio, int tipo_id, int id_especialista) {
        this.id = id;
        this.precio = precio;
        this.tipo_id = tipo_id;
        this.id_especialista = id_especialista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public int getId_especialista() {
        return id_especialista;
    }

    public void setId_especialista(int id_especialista) {
        this.id_especialista = id_especialista;
    }

    

}
