package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
@Table (name = "compra")
public class CompraModel {

    //id: INT AUTOINC UNIQUE
    //fecha_compra: DATE
    //descuento: FLOAT
    //total: FLOAT
    //id_cliente: INT → FOREIGNKEY → Cliente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="descuento")
    private float descuento;

    @Column(name="total")
    private float total;

    @Column(name="id_cliente")
    private int id_cliente;

    // Relación MUCHOS a UNO
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private ClienteModel cliente;



    public CompraModel() {
    }

    public CompraModel(Long id, float descuento, float total, int id_cliente, ClienteModel cliente) {
        this.id = id;
        this.descuento = descuento;
        this.total = total;
        this.id_cliente = id_cliente;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public ClienteModel getCliente() {
        return cliente; }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente; }

    @Override
    public String toString() {
        return "CompraModel [id=" + id + ", descuento=" + descuento + ", total=" + total + ", id_cliente=" + id_cliente
                + "]";
    }

}
