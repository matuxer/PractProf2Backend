package com.example.ferreteria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ServicioModel;

@Repository
public interface ServicioRepository extends JpaRepository<ServicioModel, Long>{
    
}
