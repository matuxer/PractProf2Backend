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
    //id_tipo: INT → FOREIGNKEY → Servicio_Tipo
    //id_especialista: INT → FOREIGNKEY → Especialista

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id; 

    @Column(name="precio")
    private float precio;

    @Column(name="id_tipo")
    private int id_tipo;

    @Column(name="id_especialista")
    private int id_especialista;

    public ServicioModel() {
    }

    public ServicioModel(Long id, float precio, int id_tipo, int id_especialista) {
        this.id = id;
        this.precio = precio;
        this.id_tipo = id_tipo;
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

    public int getid_tipo() {
        return id_tipo;
    }

    public void setid_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId_especialista() {
        return id_especialista;
    }

    public void setId_especialista(int id_especialista) {
        this.id_especialista = id_especialista;
    }

    

}
