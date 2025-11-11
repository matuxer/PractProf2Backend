package com.example.ferreteria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProductoModel;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long>, JpaSpecificationExecutor<ProductoModel> {

}
