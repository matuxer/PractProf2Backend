package com.example.ferreteria.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProductoModel;
import com.example.ferreteria.repository.ProductoRepository;

@Repository
public class ProductoDao {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoModel> obtenerTodo() {
        return productoRepository.findAll();
    }

    public ProductoModel obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public ProductoModel crear(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductoModel actualizar(Long id, ProductoModel productoActualizado) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setStock(productoActualizado.getStock());
            producto.setPrecio_unitario(productoActualizado.getPrecio_unitario());
            producto.setImgUrl(productoActualizado.getImgUrl());
            return productoRepository.save(producto);
        }).orElse(null);
    }
}
