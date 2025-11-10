package com.example.ferreteria.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


// Modelo de datos para el producto
@Entity
@Table(name = "productos")

public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "precio_unitario")
    private Float precio_unitario;

    @Column(name = "img_url")
    private String imgUrl;

    // Relación uno a muchos
    @OneToMany(mappedBy = "producto")
    @JsonBackReference
    private List<ItemModel> items = new ArrayList<>();


    // Relación con la tabla producto_categorias
    @ManyToOne
    @JoinColumn(name = "id_categoria") // nombre de la FK en la tabla productos
    @JsonIgnoreProperties("productos") // Ignora la lista de productos dentro de categoria para evitar bucle
    private ProductoCategoriaModel categoria;


    // Getters y setters
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }
    public ProductoCategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(ProductoCategoriaModel categoria) {
        this.categoria = categoria;
    }
}