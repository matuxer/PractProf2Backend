package com.example.ferreteria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ServicioModel;
import com.example.ferreteria.repository.ServicioRepository;

@Repository
public class ServicioDao {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<ServicioModel> obtenerTodo() {
        return servicioRepository.findAll();
    }

    // busca un registro por su ID
    public Optional<ServicioModel> obtenerPorId(Long id) {
        return servicioRepository.findById(id);
    }

    // guarda un nuevo registro
    public ServicioModel crear(ServicioModel servicio) {
        return servicioRepository.save(servicio);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public ServicioModel actualizar(Long id, ServicioModel servicioActualizado) {
        return servicioRepository.findById(id).map(servicio -> {
                    servicio.setPrecio(servicioActualizado.getPrecio());
                    return servicioRepository.save(servicio);
                })
                .orElse(null);
    }

}
