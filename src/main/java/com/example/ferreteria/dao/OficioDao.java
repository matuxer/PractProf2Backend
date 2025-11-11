package com.example.ferreteria.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.OficioModel;
import com.example.ferreteria.repository.OficioRepository;

@Repository
public class OficioDao {

    @Autowired
    private OficioRepository oficioRepository;

    public List<OficioModel> obtenerTodo() {
        return oficioRepository.findAll();
    }

    public OficioModel obtenerPorId(Long id) {
        return oficioRepository.findById(id).orElse(null);
    }

    public OficioModel obtenerPorNombre(String nombre) {
        return oficioRepository.findByNombre(nombre).orElse(null);
    }

    public List<OficioModel> obtenerPorCategoria(String categoria) {
        return oficioRepository.findByCategoria(categoria);
    }

    public List<OficioModel> buscarPorNombre(String nombre) {
        return oficioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public OficioModel crear(OficioModel oficio) {
        return oficioRepository.save(oficio);
    }

    public boolean eliminar(Long id) {
        if (oficioRepository.existsById(id)) {
            oficioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public OficioModel actualizar(Long id, OficioModel oficioActualizado) {
        return oficioRepository.findById(id).map(oficio -> {
            oficio.setNombre(oficioActualizado.getNombre());
            oficio.setDescripcion(oficioActualizado.getDescripcion());
            oficio.setCategoria(oficioActualizado.getCategoria());
            return oficioRepository.save(oficio);
        }).orElse(null);
    }

    public long contar() {
        return oficioRepository.count();
    }
}