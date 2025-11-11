package com.example.ferreteria.dto;

import java.util.List;

public class ClientePerfilResponse {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private int puntosRecompensa;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String pais;
    private List<CompraResumenResponse> compras;
    private List<FeedbackClienteResponse> feedbacksEscritos;

    public ClientePerfilResponse() {
    }

    public ClientePerfilResponse(Long id, String nombre, String apellido, String correo, String telefono, 
                                 int puntosRecompensa, String domicilio, String localidad, String provincia, 
                                 String pais, List<CompraResumenResponse> compras, 
                                 List<FeedbackClienteResponse> feedbacksEscritos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.puntosRecompensa = puntosRecompensa;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.compras = compras;
        this.feedbacksEscritos = feedbacksEscritos;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getPuntosRecompensa() {
        return puntosRecompensa;
    }

    public void setPuntosRecompensa(int puntosRecompensa) {
        this.puntosRecompensa = puntosRecompensa;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<CompraResumenResponse> getCompras() {
        return compras;
    }

    public void setCompras(List<CompraResumenResponse> compras) {
        this.compras = compras;
    }

    public List<FeedbackClienteResponse> getFeedbacksEscritos() {
        return feedbacksEscritos;
    }

    public void setFeedbacksEscritos(List<FeedbackClienteResponse> feedbacksEscritos) {
        this.feedbacksEscritos = feedbacksEscritos;
    }
}
