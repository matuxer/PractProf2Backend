package com.example.ferreteria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.EspecialistaModel;
import com.example.ferreteria.repository.EspecialistaRepository;

@Repository
public class EspecialistaDao {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    // devuelve todos los registros
    public List<EspecialistaModel> obtenerTodo() {
        return especialistaRepository.findAll();
    }

    // busca un registro por su ID
    public Optional<EspecialistaModel> obtenerPorId(Long id) {
        return especialistaRepository.findById(id);
    }

    // guarda un nuevo registro
    public EspecialistaModel crear(EspecialistaModel especialista) {
        return especialistaRepository.save(especialista);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (especialistaRepository.existsById(id)) {
            especialistaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public EspecialistaModel actualizar(Long id, EspecialistaModel especialistaActualizado) {
        return especialistaRepository.findById(id).map(especialista -> {
            especialista.setNombre(especialistaActualizado.getNombre());
            especialista.setApellido(especialistaActualizado.getApellido());
            especialista.setOficio(especialistaActualizado.getOficio());
            especialista.setDisponibilidad(especialistaActualizado.isDisponibilidad());
            especialista.setPuntuacion(especialistaActualizado.getPuntuacion());
            return especialistaRepository.save(especialista);
        }).orElse(null);
    }
}