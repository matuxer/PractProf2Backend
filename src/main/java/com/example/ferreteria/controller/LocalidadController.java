package com.example.ferreteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.LocalidadDao;
import com.example.ferreteria.model.LocalidadModel;

@RestController
@RequestMapping("/localidades")
public class LocalidadController {

    @Autowired
    private LocalidadDao localidadDao;

    // GET para obtener todos los recursos
    @GetMapping
    public List<LocalidadModel> getAll() {
        return localidadDao.obtenerTodo();
    }

    // GET para obtener un recurso por ID
    @GetMapping("/{id}")
    public LocalidadModel getById(@PathVariable Long id) {
        return localidadDao.obtenerPorId(id).orElse(null);
    }

    // POST para crear un nuevo recurso
    @PostMapping
    public LocalidadModel create(@RequestBody LocalidadModel localidad) {
        return localidadDao.crear(localidad);
    }

    // PUT para actualizar datos existente
    @PutMapping("/{id}")
    public LocalidadModel update(@PathVariable Long id, @RequestBody LocalidadModel localidadActualizada) {
        return localidadDao.actualizar(id, localidadActualizada);
    }

    // DELETE para eliminar por ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (localidadDao.eliminar(id)) {
            return "Localidad con ID " + id + " eliminada correctamente.";
        } else {
            return "Localidad con ID " + id + " no encontrada.";
        }
    }
}

