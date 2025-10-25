package com.example.ferreteria.repository;

import com.example.ferreteria.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {
}
