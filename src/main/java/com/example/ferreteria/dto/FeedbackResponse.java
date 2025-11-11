package com.example.ferreteria.dto;

import java.time.LocalDate;

public class FeedbackResponse {
    
    private Long id;
    private LocalDate fecha;
    private int clasificacion;
    private String comentario;
    private ClienteBasicInfo cliente;

    public FeedbackResponse() {
    }

    public FeedbackResponse(Long id, LocalDate fecha, int clasificacion, String comentario, ClienteBasicInfo cliente) {
        this.id = id;
        this.fecha = fecha;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
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

    public ClienteBasicInfo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteBasicInfo cliente) {
        this.cliente = cliente;
    }

    // Clase interna para información básica del cliente
    public static class ClienteBasicInfo {
        private Long id;
        private String nombre;
        private String apellido;

        public ClienteBasicInfo() {
        }

        public ClienteBasicInfo(Long id, String nombre, String apellido) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
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
    }
}
