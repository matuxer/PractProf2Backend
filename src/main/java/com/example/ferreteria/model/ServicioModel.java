package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

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

    @ManyToOne
    @JoinColumn(name = "id_especialista")
    private EspecialistaModel especialista;


    public ServicioModel() {
    }

    public ServicioModel(Long id, float precio, int id_tipo, EspecialistaModel especialista) {
        this.id = id;
        this.precio = precio;
        this.id_tipo = id_tipo;
        this.especialista = especialista;
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

    public EspecialistaModel getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaModel especialista) {
        this.especialista = especialista;
    }
}
