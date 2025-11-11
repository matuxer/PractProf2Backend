package com.example.ferreteria.dao;

import com.example.ferreteria.model.ServicioClienteModel;
import com.example.ferreteria.repository.ServicioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicioClienteDao {

    @Autowired
    private ServicioClienteRepository servicioClienteRepository;

    public List<ServicioClienteModel> obtenerTodo() {
        return servicioClienteRepository.findAll();
    }

    public ServicioClienteModel obtenerPorId(Long id) {
        return servicioClienteRepository.findById(id).orElse(null);
    }

    public ServicioClienteModel crear(ServicioClienteModel servicioCliente) {
        return servicioClienteRepository.save(servicioCliente);
    }

    public boolean eliminar(Long id) {
        if (servicioClienteRepository.existsById(id)) {
            servicioClienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ServicioClienteModel actualizar(Long id, ServicioClienteModel servicioClienteActualizado) {
        return servicioClienteRepository.findById(id)
                .map(servicioCliente -> {
                    servicioCliente.setServicio(servicioClienteActualizado.getServicio());
                    servicioCliente.setCliente(servicioClienteActualizado.getCliente());
                    return servicioClienteRepository.save(servicioCliente);
                })
                .orElse(null);
    }

}
