package com.example.ferreteria.model;

import jakarta.persistence.*;

@Entity
@Table(name="clientes")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", length = 50)
    private String nombre;

    @Column(name="apellido", length = 50)
    private String apellido;

    @Column(name="correo", length = 50)
    private String correo;

    @Column(name="contraseña", length = 50)
    private String contraseña;

    @Column(name = "telefono")
    private int telefono;

    @Column(name = "puntos recompensa")
    private int puntosRecompensa;

    @Column(name="domicilio", length = 50)
    private String domicilio;

    //Relacion con localidad
    @ManyToOne
    @JoinColumn(
            name = "id_localidad"
    )
    private LocalidadModel localidad;

    //Relacion con provincia
    @ManyToOne
    @JoinColumn(
            name = "id_provincia"
    )
    private ProvinciaModel provincia;

    //Relacion con pais
    @ManyToOne
    @JoinColumn(
            name = "id_pais"
    )
    private PaisModel pais;

    public ClienteModel() {
    }

    public ClienteModel(Long id, String nombre, String apellido, String correo, String contraseña, int telefono, int puntosRecompensa, String domicilio, LocalidadModel localidad, ProvinciaModel provincia, PaisModel pais) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.puntosRecompensa = puntosRecompensa;
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
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

    public LocalidadModel getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadModel localidad) {
        this.localidad = localidad;
    }

    public ProvinciaModel getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaModel provincia) {
        this.provincia = provincia;
    }

    public PaisModel getPais() {
        return pais;
    }

    public void setPais(PaisModel pais) {
        this.pais = pais;
    }
}
