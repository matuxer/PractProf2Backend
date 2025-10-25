package com.example.ferreteria.controller;


import com.example.ferreteria.dao.ClienteDao;
import com.example.ferreteria.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteDao clienteDao;

    @GetMapping
    public List<ClienteModel> getAll(){
        return clienteDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public ClienteModel getById(@PathVariable Long id){
        return clienteDao.obtenerPorId(id);
    }

    @PostMapping
    public ClienteModel create(@RequestBody ClienteModel cliente){
        return clienteDao.crear(cliente);
    }

    @PutMapping("/{id}")
    public ClienteModel update(@PathVariable Long id, @RequestBody ClienteModel cliente){
        cliente.setId(id);
        return clienteDao.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        if (clienteDao.eliminar(id)){
            return "El cliente con ID " + id + "eliminado correctamente";
        }else {
            return "El cliente con ID" + id + "no encontrado";
        }
    }
}
