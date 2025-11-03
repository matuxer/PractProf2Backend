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


    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precio_unitario")
    private float precio_unitario;

    // Relación Muchos a uno
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoModel producto;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private CompraModel compra;


    public ItemModel() {
    }


    public ItemModel(Long id, int cantidad, float precio_unitario, ProductoModel producto, CompraModel compra) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.producto = producto;
        this.compra = compra;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public CompraModel getCompra() {
        return compra;
    }

    public void setCompra(CompraModel compra) {
        this.compra = compra;
    }


    @Override
    public String toString() {
        return "ItemModel [id=" + id +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                ", producto=" + (producto != null ? producto.getId() : null) +
                ", compra=" + (compra != null ? compra.getId() : null)+ "]";
    }
}
