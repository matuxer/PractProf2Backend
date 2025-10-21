package com.example.ferreteria.repository;

import com.example.ferreteria.model.PaisModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaisRepository extends JpaRepository<PaisModel, Long> {

}
