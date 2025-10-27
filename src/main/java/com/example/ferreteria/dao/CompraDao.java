package com.example.ferreteria.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.CompraModel;
import com.example.ferreteria.repository.CompraRepository;

@Repository
public class CompraDao {
    
    @Autowired
    private CompraRepository compraRepository;

    // devuelve todos los registros
    public List<CompraModel> obtenerTodo() {
        return compraRepository.findAll();
    }

    // busca un registro por su ID
    public Optional<CompraModel> obtenerPorId(Long id) {
        return compraRepository.findById(id);
    }

    // guarda un nuevo registro
    public CompraModel crear(CompraModel compra) {
        return compraRepository.save(compra);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (compraRepository.existsById(id)) {
            compraRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public CompraModel actualizar(Long id, CompraModel compraActualizado) {
        return compraRepository.findById(id).map(item -> {
            item.setDescuento(compraActualizado.getDescuento());
            item.setTotal(compraActualizado.getTotal());
            item.setId_cliente(compraActualizado.getId_cliente());
            return compraRepository.save(item);
        }).orElse(null);
    }


}
