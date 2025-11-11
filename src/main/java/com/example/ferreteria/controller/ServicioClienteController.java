package com.example.ferreteria.controller;

import com.example.ferreteria.dao.ServicioClienteDao;
import com.example.ferreteria.model.ServicioClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicio-clientes")
public class ServicioClienteController {

    @Autowired
    private ServicioClienteDao servicioClienteDao;

    @GetMapping
    public List<ServicioClienteModel> getAll() {
        return servicioClienteDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public ServicioClienteModel getById(@PathVariable Long id) {
        return servicioClienteDao.obtenerPorId(id);
    }

    @PostMapping
    public ServicioClienteModel create(@RequestBody ServicioClienteModel servicioCliente) {
        return servicioClienteDao.crear(servicioCliente);
    }

    @PutMapping("/{id}")
    public ServicioClienteModel update(@PathVariable Long id, @RequestBody ServicioClienteModel servicioCliente) {
        servicioCliente.setId(id);
        return servicioClienteDao.actualizar(id, servicioCliente);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (servicioClienteDao.eliminar(id)) {
            return "El servicio-cliente con ID " + id + " eliminado correctamente";
        } else {
            return "El servicio-cliente con ID " + id + " no encontrado";
        }
    }
}
