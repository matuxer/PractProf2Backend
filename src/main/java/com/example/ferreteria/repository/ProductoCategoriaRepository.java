package com.example.ferreteria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProductoCategoriaModel;

@Repository
public interface ProductoCategoriaRepository extends JpaRepository<ProductoCategoriaModel, Long> {
    
    // Spring Data JPA generará automáticamente este método basándose en el campo 'nombre'
    Optional<ProductoCategoriaModel> findByNombre(String nombre);
}
