package com.example.ferreteria.dto;

import java.time.LocalDate;
import java.util.List;

public class CompraResumenResponse {
    
    private Long id;
    private LocalDate fecha;
    private float descuento;
    private float total;
    private List<ItemResumen> items;

    public CompraResumenResponse() {
    }

    public CompraResumenResponse(Long id, LocalDate fecha, float descuento, float total, List<ItemResumen> items) {
        this.id = id;
        this.fecha = fecha;
        this.descuento = descuento;
        this.total = total;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
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

    public List<ItemResumen> getItems() {
        return items;
    }

    public void setItems(List<ItemResumen> items) {
        this.items = items;
    }

    // Clase interna para items
    public static class ItemResumen {
        private Long id;
        private int cantidad;
        private float precioTotal;
        private ProductoBasicInfo producto;

        public ItemResumen() {
        }

        public ItemResumen(Long id, int cantidad, float precioTotal, ProductoBasicInfo producto) {
            this.id = id;
            this.cantidad = cantidad;
            this.precioTotal = precioTotal;
            this.producto = producto;
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

        public float getPrecioTotal() {
            return precioTotal;
        }

        public void setPrecioTotal(float precioTotal) {
            this.precioTotal = precioTotal;
        }

        public ProductoBasicInfo getProducto() {
            return producto;
        }

        public void setProducto(ProductoBasicInfo producto) {
            this.producto = producto;
        }
    }

    // Clase interna para información básica del producto
    public static class ProductoBasicInfo {
        private Long id;
        private String nombre;
        private float precioUnitario;
        private String imgUrl;

        public ProductoBasicInfo() {
        }

        public ProductoBasicInfo(Long id, String nombre, float precioUnitario, String imgUrl) {
            this.id = id;
            this.nombre = nombre;
            this.precioUnitario = precioUnitario;
            this.imgUrl = imgUrl;
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

        public float getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(float precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
