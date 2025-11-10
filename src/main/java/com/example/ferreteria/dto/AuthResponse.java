package com.example.ferreteria.dto;

import com.example.ferreteria.model.ClienteModel;

public class AuthResponse {
    private String message;
    private ClienteModel cliente;

    public AuthResponse() {
    }

    public AuthResponse(String message, ClienteModel cliente) {
        this.message = message;
        this.cliente = cliente;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
}
