package com.example.ferreteria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.repository.ClienteRepository;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Registra un nuevo cliente con contraseña encriptada
     */
    public ClienteModel registrar(ClienteModel cliente) {
        // Verificar si el correo ya existe
        if (clienteRepository.existsByCorreo(cliente.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Encriptar la contraseña
        String passwordEncriptada = passwordEncoder.encode(cliente.getContraseña());
        cliente.setContraseña(passwordEncriptada);

        // Inicializar puntos de recompensa en 0
        cliente.setPuntosRecompensa(0);

        // Guardar el cliente
        return clienteRepository.save(cliente);
    }

    /**
     * Autentica un cliente con correo y contraseña
     */
    public Optional<ClienteModel> login(String correo, String password) {
        Optional<ClienteModel> clienteOpt = clienteRepository.findByCorreo(correo);

        if (clienteOpt.isPresent()) {
            ClienteModel cliente = clienteOpt.get();
            
            // Verificar la contraseña
            if (passwordEncoder.matches(password, cliente.getContraseña())) {
                return Optional.of(cliente);
            }
        }

        return Optional.empty();
    }

    /**
     * Verifica si un correo ya está registrado
     */
    public boolean existeCorreo(String correo) {
        return clienteRepository.existsByCorreo(correo);
    }
}
