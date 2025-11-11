package com.example.ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ferreteria.model.ServicioClienteModel;

public interface ServicioClienteRepository extends JpaRepository<ServicioClienteModel, Long> {

}
