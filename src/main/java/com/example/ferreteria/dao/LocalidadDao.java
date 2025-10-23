package com.example.ferreteria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.LocalidadModel;
import com.example.ferreteria.repository.LocalidadRepository;

@Repository
public class LocalidadDao {

    @Autowired
    private LocalidadRepository localidadRepository;

    // devuelve todos los registros
    public List<LocalidadModel> obtenerTodo() {
        return localidadRepository.findAll();
    }

    // busca un registro por su ID
    public Optional<LocalidadModel> obtenerPorId(Long id) {
        return localidadRepository.findById(id);
    }

    // guarda un nuevo registro
    public LocalidadModel crear(LocalidadModel localidad) {
        return localidadRepository.save(localidad);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (localidadRepository.existsById(id)) {
            localidadRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public LocalidadModel actualizar(Long id, LocalidadModel localidadActualizada) {
        return localidadRepository.findById(id)
                .map(localidad -> {
                    localidad.setNombre(localidadActualizada.getNombre());
                    return localidadRepository.save(localidad);
                })
                .orElse(null);
    }
}
