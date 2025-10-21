package model;

import jakarta.persistence.*;

@Entity
@Table(name = "especialistas")
public class EspecialistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String oficio;
    private boolean disponibilidad;
    private int puntuacion;

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

