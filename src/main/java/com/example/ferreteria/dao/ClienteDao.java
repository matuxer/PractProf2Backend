package com.example.ferreteria.dao;

import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDao {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> obtenerTodo(){
        return clienteRepository.findAll();
    }

    public ClienteModel obtenerPorId(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public ClienteModel crear(ClienteModel cliente){
        return clienteRepository.save(cliente);
    }

    public boolean eliminar(Long id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ClienteModel actualizar(Long id, ClienteModel clienteactualizado){
        return clienteRepository.findById(id).map(cliente ->{
            cliente.setNombre(clienteactualizado.getNombre());
            cliente.setApellido(clienteactualizado.getApellido());
            cliente.setCorreo(clienteactualizado.getCorreo());
            cliente.setContraseña(clienteactualizado.getContraseña());
            cliente.setPuntosRecompensa(clienteactualizado.getPuntosRecompensa());
            cliente.setDomicilio(clienteactualizado.getDomicilio());
            cliente.setLocalidad(clienteactualizado.getLocalidad());
            cliente.setProvincia(clienteactualizado.getProvincia());
            cliente.setPais(clienteactualizado.getPais());
            return clienteRepository.save(cliente);
        }).orElse(null);
    }
}
