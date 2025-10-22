package com.example.ferreteria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProvinciaModel;
import com.example.ferreteria.repository.ProvinciaRepository;

@Repository
public class ProvinciaDao {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    // devuelve todos los registros
    public List<ProvinciaModel> obtenerTodo() {
        return provinciaRepository.findAll();
    }

    // busca un registro por su ID
    public Optional<ProvinciaModel> obtenerPorId(Long id) {
        return provinciaRepository.findById(id);
    }

    // guarda un nuevo registro
    public ProvinciaModel crear(ProvinciaModel provincia) {
        return provinciaRepository.save(provincia);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (provinciaRepository.existsById(id)) {
            provinciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public ProvinciaModel actualizar(Long id, ProvinciaModel provinciaActualizada) {
        return provinciaRepository.findById(id)
                .map(provincia -> {
                    provincia.setNombre(provinciaActualizada.getNombre());
                    return provinciaRepository.save(provincia);
                })
                .orElse(null);
    }

}

