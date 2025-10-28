package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "especialistas")
public class EspecialistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "oficio", length = 100)
    private String oficio;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @Column(name = "puntuacion")
    private int puntuacion;

    @OneToMany(mappedBy = "especialista")
    private List<FeedbackModel> feedbacksRecibidos = new ArrayList<>();

    public EspecialistaModel() {}

    public EspecialistaModel(String nombre, String apellido, String oficio, boolean disponibilidad, int puntuacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.oficio = oficio;
        this.disponibilidad = disponibilidad;
        this.puntuacion = puntuacion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<FeedbackModel> getFeedbacksRecibidos() {
        return feedbacksRecibidos;
    }

    public void setFeedbacksRecibidos(List<FeedbackModel> feedbacksRecibidos) {
        this.feedbacksRecibidos = feedbacksRecibidos;
    }

    @Override
    public String toString() {
        return "EspecialistaModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", oficio='" + oficio + '\'' +
                ", disponibilidad=" + disponibilidad +
                ", puntuacion=" + puntuacion +
                '}';
    }
}