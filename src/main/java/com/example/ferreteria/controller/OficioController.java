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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.OficioDao;
import com.example.ferreteria.model.OficioModel;

@RestController
@RequestMapping("/oficios")
public class OficioController {

    @Autowired
    private OficioDao oficioDao;

    @GetMapping
    public List<OficioModel> getAll() {
        return oficioDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public OficioModel getById(@PathVariable Long id) {
        return oficioDao.obtenerPorId(id);
    }

    @GetMapping("/buscar/{nombre}")
    public OficioModel getByNombre(@PathVariable String nombre) {
        return oficioDao.obtenerPorNombre(nombre);
    }

    @GetMapping("/categoria/{categoria}")
    public List<OficioModel> getByCategoria(@PathVariable String categoria) {
        return oficioDao.obtenerPorCategoria(categoria);
    }

    @GetMapping("/buscar")
    public List<OficioModel> buscarPorNombre(@RequestParam String nombre) {
        return oficioDao.buscarPorNombre(nombre);
    }

    @PostMapping
    public OficioModel create(@RequestBody OficioModel oficio) {
        return oficioDao.crear(oficio);
    }

    @PutMapping("/{id}")
    public OficioModel update(@PathVariable Long id, @RequestBody OficioModel oficio) {
        return oficioDao.actualizar(id, oficio);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (oficioDao.eliminar(id)) {
            return "Oficio con ID " + id + " eliminado correctamente.";
        } else {
            return "Oficio con ID " + id + " no encontrado.";
        }
    }

    @GetMapping("/count")
    public long count() {
        return oficioDao.contar();
    }
}