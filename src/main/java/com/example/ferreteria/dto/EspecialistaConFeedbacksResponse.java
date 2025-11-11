package com.example.ferreteria.dto;

import java.util.List;

public class EspecialistaConFeedbacksResponse {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String oficio;
    private boolean disponibilidad;
    private int puntuacion;
    private String perfilImgUrl;
    private List<FeedbackResponse> feedbacksRecibidos;

    public EspecialistaConFeedbacksResponse() {
    }

    public EspecialistaConFeedbacksResponse(Long id, String nombre, String apellido, String oficio, 
                                            boolean disponibilidad, int puntuacion, String perfilImgUrl, 
                                            List<FeedbackResponse> feedbacksRecibidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.oficio = oficio;
        this.disponibilidad = disponibilidad;
        this.puntuacion = puntuacion;
        this.perfilImgUrl = perfilImgUrl;
        this.feedbacksRecibidos = feedbacksRecibidos;
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

    public String getPerfilImgUrl() {
        return perfilImgUrl;
    }

    public void setPerfilImgUrl(String perfilImgUrl) {
        this.perfilImgUrl = perfilImgUrl;
    }

    public List<FeedbackResponse> getFeedbacksRecibidos() {
        return feedbacksRecibidos;
    }

    public void setFeedbacksRecibidos(List<FeedbackResponse> feedbacksRecibidos) {
        this.feedbacksRecibidos = feedbacksRecibidos;
    }
}
