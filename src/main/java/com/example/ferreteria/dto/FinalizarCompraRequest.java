package com.example.ferreteria.dto;

import java.util.List;

public class FinalizarCompraRequest {
    
    private Long clienteId;
    private float descuento;
    private List<ItemRequest> items;

    public FinalizarCompraRequest() {
    }

    public FinalizarCompraRequest(Long clienteId, float descuento, List<ItemRequest> items) {
        this.clienteId = clienteId;
        this.descuento = descuento;
        this.items = items;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }
}
