package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    // Relación Muchos a uno
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoModel producto;


    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_total")
    private float precio_total;


    public ItemModel() {
    }


    public ItemModel(Long id, ProductoModel producto, int cantidad, float precio_total) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_total = precio_total;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }

    @Override
    public String toString() {
        return "ItemModel [id=" + id +
                ", producto=" + (producto != null ? producto.getId() : null) +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_total + "]";
    }
}
