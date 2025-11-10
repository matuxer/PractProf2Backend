package com.example.ferreteria.dto;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private String telefono;
    private String domicilio;
    
    // Puede venir el ID o el nombre del país
    private Long idPais;
    private String nombrePais;
    
    // Puede venir el ID o el nombre de la provincia
    private Long idProvincia;
    private String nombreProvincia;
    
    // Puede venir el ID o el nombre de la localidad
    private Long idLocalidad;
    private String nombreLocalidad;

    public RegisterRequest() {
    }

    public RegisterRequest(String nombre, String apellido, String correo, String password, String telefono, 
                          String domicilio, Long idPais, String nombrePais, Long idProvincia, 
                          String nombreProvincia, Long idLocalidad, String nombreLocalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.idPais = idPais;
        this.nombrePais = nombrePais;
        this.idProvincia = idProvincia;
        this.nombreProvincia = nombreProvincia;
        this.idLocalidad = idLocalidad;
        this.nombreLocalidad = nombreLocalidad;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getIdPais() {
        return idPais;
    }

    public void setIdPais(Long idPais) {
        this.idPais = idPais;
    }

    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Long getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }
}
