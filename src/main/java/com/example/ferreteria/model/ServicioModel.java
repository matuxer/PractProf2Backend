package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    //@Column(name="id_tipo")
    @ManyToOne
    @JoinColumn(name="id_tipo")
    private TipoServicioModel tipoServicio;

    @ManyToOne
    @JoinColumn(name = "id_especialista")
    private EspecialistaModel especialista;


    public ServicioModel() {
    }

    public ServicioModel(Long id, float precio, TipoServicioModel tipoServicio, EspecialistaModel especialista) {
        this.id = id;
        this.precio = precio;
        this.tipoServicio = tipoServicio;
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

    public TipoServicioModel getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicioModel tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public EspecialistaModel getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaModel especialista) {
        this.especialista = especialista;
    }

}
