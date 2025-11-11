package com.example.ferreteria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.OficioModel;

@Repository
public interface OficioRepository extends JpaRepository<OficioModel, Long> {
    
    // Spring Data JPA generará automáticamente estos métodos
    Optional<OficioModel> findByNombre(String nombre);
    List<OficioModel> findByCategoria(String categoria);
    List<OficioModel> findByNombreContainingIgnoreCase(String nombre);
}