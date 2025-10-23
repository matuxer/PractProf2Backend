package com.example.ferreteria.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProductoCategoriaModel;
import com.example.ferreteria.repository.ProductoCategoriaRepository;

@Repository
public class ProductoCategoriaDao {

    @Autowired
    private ProductoCategoriaRepository productoCategoriaRepository;

    public List<ProductoCategoriaModel> obtenerTodo() {
        return productoCategoriaRepository.findAll();
    }

    public ProductoCategoriaModel obtenerPorId(Long id) {
        return productoCategoriaRepository.findById(id).orElse(null);
    }

    public ProductoCategoriaModel crear(ProductoCategoriaModel productoCategoria) {
        return productoCategoriaRepository.save(productoCategoria);
    }

    public boolean eliminar(Long id) {
        if (productoCategoriaRepository.existsById(id)) {
            productoCategoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductoCategoriaModel actualizar(Long id, ProductoCategoriaModel productoCategoriaActualizado) {
        return productoCategoriaRepository.findById(id).map(productoCategoria -> {
            productoCategoria.setNombre(productoCategoriaActualizado.getNombre());
            return productoCategoriaRepository.save(productoCategoria);
        }).orElse(null);
    }
}
