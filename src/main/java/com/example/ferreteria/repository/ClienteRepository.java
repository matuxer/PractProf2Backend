package com.example.ferreteria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ferreteria.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
    Optional<ClienteModel> findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}
