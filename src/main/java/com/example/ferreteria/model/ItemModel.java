package com.example.ferreteria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "item")
public class ItemModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id; 

   // @ManyToOne // Un Item pertenece a una Compra
   // @JoinColumn(name = "id_compra") 
   //private CompraModel compra;


   // @ManyToOne // Un Item está relacionado con un Producto
   // @JoinColumn(name = "id_producto")
   // private ProductoModel producto;

    //cantidad: INT

    @Column(name="precio_unitario")
    private float precio_unitario;

    public ItemModel() {
    }

    public ItemModel(int id, /*CompraModel compra, ProductoModel producto,*/ float precio_unitario) {
        this.id = id;
       // this.compra = compra;
       // this.producto = producto;
        this.precio_unitario = precio_unitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   /*  public CompraModel getCompra() {
        return compra;
    }

    public void setCompra(CompraModel compra) {
        this.compra = compra;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto; */
    

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    @Override
    public String toString() {
        return "ItemModel [id=" + id + ", precio_unitario=" + precio_unitario + ", getId()=" + getId()
                + ", getPrecio_unitario()=" + getPrecio_unitario() + "]";
    }
    
}
