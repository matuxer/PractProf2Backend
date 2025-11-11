package com.example.ferreteria.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "especialistas")
public class EspecialistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    // Relación con oficios
    @ManyToOne
    @JoinColumn(name = "id_oficio")
    @JsonIgnoreProperties("especialistas") // Ignora la lista de especialistas dentro de oficio para evitar bucle
    private OficioModel oficio;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @Column(name = "puntuacion")
    private int puntuacion;

    @Column(name = "perfil_img_url")
    private String perfilImgUrl;

    @OneToMany(mappedBy = "especialista")
    private List<FeedbackModel> feedbacksRecibidos = new ArrayList<>();
  
    @OneToMany(mappedBy = "especialista")
    @JsonIgnoreProperties("especialista") // Ignora especialista dentro de servicio para evitar bucle
    private List<ServicioModel> servicios = new ArrayList<>();

    public EspecialistaModel() {}

    public EspecialistaModel(String nombre, String apellido, OficioModel oficio, boolean disponibilidad, int puntuacion, String perfilImgUrl) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.oficio = oficio;
        this.disponibilidad = disponibilidad;
        this.puntuacion = puntuacion;
        this.perfilImgUrl = perfilImgUrl;
    }

    // Constructor sin perfil_img_url (para compatibilidad)
    public EspecialistaModel(String nombre, String apellido, OficioModel oficio, boolean disponibilidad, int puntuacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.oficio = oficio;
        this.disponibilidad = disponibilidad;
        this.puntuacion = puntuacion;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public OficioModel getOficio() {
        return oficio;
    }

    public void setOficio(OficioModel oficio) {
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

    public String getPerfilImgUrl() {
        return perfilImgUrl;
    }

    public void setPerfilImgUrl(String perfilImgUrl) {
        this.perfilImgUrl = perfilImgUrl;
    }

    public List<FeedbackModel> getFeedbacksRecibidos() {
        return feedbacksRecibidos;
    }

    public void setFeedbacksRecibidos(List<FeedbackModel> feedbacksRecibidos) {
        this.feedbacksRecibidos = feedbacksRecibidos;
    }

    public List<ServicioModel> getServicios() {
        return servicios;
    }

    public void setServicios(List<ServicioModel> servicios) {
        this.servicios = servicios;
    }

    @Override
    public String toString() {
        return "EspecialistaModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", oficio=" + (oficio != null ? oficio.getNombre() : null) +
                ", disponibilidad=" + disponibilidad +
                ", puntuacion=" + puntuacion +
                '}';
    }
}