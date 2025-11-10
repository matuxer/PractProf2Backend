package com.example.ferreteria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dto.AuthResponse;
import com.example.ferreteria.dto.LoginRequest;
import com.example.ferreteria.dto.RegisterRequest;
import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint para registrar un nuevo cliente
     * POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            // Crear el modelo de cliente
            ClienteModel cliente = new ClienteModel();
            cliente.setNombre(request.getNombre());
            cliente.setApellido(request.getApellido());
            cliente.setCorreo(request.getCorreo());
            cliente.setContraseña(request.getPassword());
            cliente.setTelefono(request.getTelefono());
            cliente.setDomicilio(request.getDomicilio());

            // Registrar el cliente (la contraseña se encripta automáticamente)
            ClienteModel clienteRegistrado = authService.registrar(cliente);

            // No devolver la contraseña en la respuesta
            clienteRegistrado.setContraseña(null);

            AuthResponse response = new AuthResponse("Cliente registrado exitosamente", clienteRegistrado);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            AuthResponse response = new AuthResponse(e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Endpoint para login de cliente
     * POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request.getCorreo(), request.getPassword())
            .map(cliente -> {
                // No devolver la contraseña en la respuesta
                cliente.setContraseña(null);
                
                AuthResponse response = new AuthResponse("Login exitoso", cliente);
                return ResponseEntity.ok(response);
            })
            .orElseGet(() -> {
                AuthResponse response = new AuthResponse("Credenciales inválidas", null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            });
    }
}
