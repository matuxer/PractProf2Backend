package com.example.ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ferreteria.model.LocalidadModel;


public interface LocalidadRepository extends JpaRepository<LocalidadModel, Long> {
}
