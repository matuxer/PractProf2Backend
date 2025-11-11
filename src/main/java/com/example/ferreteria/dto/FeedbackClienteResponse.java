package com.example.ferreteria.dto;

import java.time.LocalDate;

public class FeedbackClienteResponse {
    
    private Long id;
    private LocalDate fecha;
    private int clasificacion;
    private String comentario;
    private EspecialistaBasicInfo especialista;

    public FeedbackClienteResponse() {
    }

    public FeedbackClienteResponse(Long id, LocalDate fecha, int clasificacion, String comentario, EspecialistaBasicInfo especialista) {
        this.id = id;
        this.fecha = fecha;
        this.clasificacion = clasificacion;
        this.comentario = comentario;
        this.especialista = especialista;
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

    public EspecialistaBasicInfo getEspecialista() {
        return especialista;
    }

    public void setEspecialista(EspecialistaBasicInfo especialista) {
        this.especialista = especialista;
    }

    // Clase interna para información básica del especialista
    public static class EspecialistaBasicInfo {
        private Long id;
        private String nombre;
        private String apellido;
        private String oficio;
        private String perfilImgUrl;

        public EspecialistaBasicInfo() {
        }

        public EspecialistaBasicInfo(Long id, String nombre, String apellido, String oficio, String perfilImgUrl) {
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.oficio = oficio;
            this.perfilImgUrl = perfilImgUrl;
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

        public String getPerfilImgUrl() {
            return perfilImgUrl;
        }

        public void setPerfilImgUrl(String perfilImgUrl) {
            this.perfilImgUrl = perfilImgUrl;
        }
    }
}
