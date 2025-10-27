package com.example.ferreteria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.CompraModel;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long>{
    
}
