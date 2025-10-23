package com.example.ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProductoCategoriaModel;

@Repository
public interface ProductoCategoriaRepository extends JpaRepository<ProductoCategoriaModel, Long> {

}
