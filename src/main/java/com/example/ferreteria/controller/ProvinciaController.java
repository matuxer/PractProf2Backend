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

import com.example.ferreteria.dao.ProvinciaDao;
import com.example.ferreteria.model.ProvinciaModel;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaDao provinciaDao;

    // GET para obtener todas las provincias
    @GetMapping
    public List<ProvinciaModel> getAll() {
        return provinciaDao.obtenerTodo();
    }

    // GET para obtener una provincia por ID
    @GetMapping("/{id}")
    public ProvinciaModel getById(@PathVariable Long id) {
        return provinciaDao.obtenerPorId(id).orElse(null);
    }

    // POST para crear una nueva provincia
    @PostMapping
    public ProvinciaModel create(@RequestBody ProvinciaModel provincia) {
        return provinciaDao.crear(provincia);
    }

    // PUT para actualizar una provincia existente
    @PutMapping("/{id}")
    public ProvinciaModel update(@PathVariable Long id, @RequestBody ProvinciaModel provinciaActualizada) {
        return provinciaDao.actualizar(id, provinciaActualizada);
    }

    // DELETE para eliminar una provincia por ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (provinciaDao.eliminar(id)) {
            return "Provincia con ID " + id + " eliminada correctamente.";
        } else {
            return "Provincia con ID " + id + " no encontrada.";
        }
    }
}

