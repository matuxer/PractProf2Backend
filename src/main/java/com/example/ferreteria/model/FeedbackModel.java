package com.example.ferreteria.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedbacks")
public class FeedbackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "clasificacion")
    private int clasificacion;

    @Column(name = "comentario", length = 255)
    private String comentario;

    // Relación con Especialista
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_especialista")
    private EspecialistaModel especialista;
  
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;


    public FeedbackModel() {
    }


    public FeedbackModel(LocalDate fecha, int clasificacion, String comentario, ClienteModel cliente,EspecialistaModel especialista) {
        this.fecha = fecha;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
        this.especialista = especialista;
        this.cliente = cliente;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public EspecialistaModel getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaModel especialista) {
        this.especialista = especialista;
    }
    public ClienteModel getCliente() {

        return cliente;
    }

    public void setCliente(ClienteModel cliente) {

        this.cliente = cliente;
    }
}
