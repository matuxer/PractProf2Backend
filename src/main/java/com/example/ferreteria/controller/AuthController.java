package com.example.ferreteria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.LocalidadDao;
import com.example.ferreteria.dao.PaisDao;
import com.example.ferreteria.dao.ProvinciaDao;
import com.example.ferreteria.dto.AuthResponse;
import com.example.ferreteria.dto.LoginRequest;
import com.example.ferreteria.dto.RegisterRequest;
import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.model.LocalidadModel;
import com.example.ferreteria.model.PaisModel;
import com.example.ferreteria.model.ProvinciaModel;
import com.example.ferreteria.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private PaisDao paisDao;

    @Autowired
    private ProvinciaDao provinciaDao;

    @Autowired
    private LocalidadDao localidadDao;

    /**
     * Endpoint para registrar un nuevo cliente
     * POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        try {
            // Obtener o crear país
            PaisModel pais = null;
            if (request.getIdPais() != null) {
                pais = paisDao.obtenerPorId(request.getIdPais());
                if (pais == null) {
                    throw new RuntimeException("País con ID " + request.getIdPais() + " no encontrado");
                }
            } else if (request.getNombrePais() != null && !request.getNombrePais().trim().isEmpty()) {
                // Crear nuevo país si no existe
                PaisModel nuevoPais = new PaisModel();
                nuevoPais.setNombre(request.getNombrePais().trim());
                pais = paisDao.crear(nuevoPais);
            }

            // Obtener o crear provincia
            ProvinciaModel provincia = null;
            if (request.getIdProvincia() != null) {
                provincia = provinciaDao.obtenerPorId(request.getIdProvincia())
                    .orElseThrow(() -> new RuntimeException("Provincia con ID " + request.getIdProvincia() + " no encontrada"));
            } else if (request.getNombreProvincia() != null && !request.getNombreProvincia().trim().isEmpty()) {
                // Crear nueva provincia si no existe
                ProvinciaModel nuevaProvincia = new ProvinciaModel();
                nuevaProvincia.setNombre(request.getNombreProvincia().trim());
                provincia = provinciaDao.crear(nuevaProvincia);
            }

            // Obtener o crear localidad
            LocalidadModel localidad = null;
            if (request.getIdLocalidad() != null) {
                localidad = localidadDao.obtenerPorId(request.getIdLocalidad())
                    .orElseThrow(() -> new RuntimeException("Localidad con ID " + request.getIdLocalidad() + " no encontrada"));
            } else if (request.getNombreLocalidad() != null && !request.getNombreLocalidad().trim().isEmpty()) {
                // Crear nueva localidad si no existe
                LocalidadModel nuevaLocalidad = new LocalidadModel();
                nuevaLocalidad.setNombre(request.getNombreLocalidad().trim());
                localidad = localidadDao.crear(nuevaLocalidad);
            }

            // Crear el modelo de cliente
            ClienteModel cliente = new ClienteModel();
            cliente.setNombre(request.getNombre());
            cliente.setApellido(request.getApellido());
            cliente.setCorreo(request.getCorreo());
            cliente.setContraseña(request.getPassword());
            cliente.setTelefono(request.getTelefono());
            cliente.setDomicilio(request.getDomicilio());
            cliente.setPais(pais);
            cliente.setProvincia(provincia);
            cliente.setLocalidad(localidad);

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
