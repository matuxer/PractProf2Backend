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

import com.example.ferreteria.dao.ProductoDao;
import com.example.ferreteria.model.ProductoModel;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoDao productoDao;

    @GetMapping
    public List<ProductoModel> getAll() {
        return productoDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public ProductoModel getById(@PathVariable Long id) {
        return productoDao.obtenerPorId(id);
    }

    @PostMapping
    public ProductoModel create(@RequestBody ProductoModel producto) {
        return productoDao.crear(producto);
    }

    @PutMapping("/{id}")
    public ProductoModel update(@PathVariable Long id, @RequestBody ProductoModel producto) {
        producto.setId(id);
        return productoDao.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (productoDao.eliminar(id)) {
            return "Producto con ID " + id + " eliminado correctamente.";
        } else {
            return "Producto con ID " + id + " no encontrado.";
        }
    }
}
