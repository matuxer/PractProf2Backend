package com.example.ferreteria.dto;

public class CrearFeedbackRequest {
    
    private Long clienteId;
    private Long especialistaId;
    private int clasificacion; // 1-5 estrellas
    private String comentario;

    public CrearFeedbackRequest() {
    }

    public CrearFeedbackRequest(Long clienteId, Long especialistaId, int clasificacion, String comentario) {
        this.clienteId = clienteId;
        this.especialistaId = especialistaId;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEspecialistaId() {
        return especialistaId;
    }

    public void setEspecialistaId(Long especialistaId) {
        this.especialistaId = especialistaId;
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
}
