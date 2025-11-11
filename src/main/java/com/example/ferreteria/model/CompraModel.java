package com.example.ferreteria.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra")
public class CompraModel {

    //id: INT AUTOINC UNIQUE
    //fecha_compra: DATE
    //descuento: FLOAT
    //total: FLOAT
    //id_cliente: INT → FOREIGNKEY → Cliente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(name = "descuento")
    private float descuento;

    @Column(name = "total")
    private float total;

    @OneToMany(mappedBy = "compra")
    private List<ItemModel> items = new ArrayList<>();

    // Relación MUCHOS a UNO
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteModel cliente;

    public CompraModel() {
    }

    public CompraModel(Long id, LocalDate fechaCompra, float descuento, float total, ClienteModel cliente) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.descuento = descuento;
        this.total = total;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
