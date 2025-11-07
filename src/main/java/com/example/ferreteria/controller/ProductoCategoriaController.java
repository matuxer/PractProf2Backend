package com.example.ferreteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.ProductoCategoriaDao;
import com.example.ferreteria.model.ProductoCategoriaModel;

@RestController
@RequestMapping("/producto-categorias")
public class ProductoCategoriaController {

    @Autowired
    private ProductoCategoriaDao productoCategoriaDao;

    @GetMapping
    public List<ProductoCategoriaModel> getAll() {
        return productoCategoriaDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public ProductoCategoriaModel getById(@PathVariable Long id) {
        return productoCategoriaDao.obtenerPorId(id);
    }

    @PostMapping
    public ProductoCategoriaModel create(@RequestBody ProductoCategoriaModel productoCategoria) {
        return productoCategoriaDao.crear(productoCategoria);
    }

    @PutMapping("/{id}")
    public ProductoCategoriaModel update(@PathVariable Long id, @RequestBody ProductoCategoriaModel productoCategoria) {
        productoCategoria.setId(id);
        return productoCategoriaDao.actualizar(id, productoCategoria);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (productoCategoriaDao.eliminar(id)) {
            return "ProductoCategoria con ID " + id + " eliminado correctamente.";
        } else {
            return "ProductoCategoria con ID " + id + " no encontrado.";
        }
    }
}
