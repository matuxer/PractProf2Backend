package com.example.ferreteria.dto;

import com.example.ferreteria.model.ClienteModel;

public class AuthResponse {
    private String message;
    private ClienteModel cliente;
    private String token;

    public AuthResponse() {
    }

    public AuthResponse(String message, ClienteModel cliente, String token) {
        this.message = message;
        this.cliente = cliente;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
