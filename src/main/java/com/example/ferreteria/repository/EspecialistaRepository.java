package com.example.ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.EspecialistaModel;

@Repository
public interface EspecialistaRepository extends JpaRepository<EspecialistaModel, Integer> {

}