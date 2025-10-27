package com.example.ferreteria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.CompraDao;
import com.example.ferreteria.model.CompraModel;

@RestController
@RequestMapping("/compra")
public class CompraController {
      
//-GetAll (Obtener todos los registros) GET
//-GetById (Obtener 1 solo registro por Id) GET
//-Create (Crear un registro) POST
//-Update (Actualizar un registro) PUT
//-Delete (Borrar un registro) DELETE

    @Autowired
    private CompraDao compraDao;

    @GetMapping
    public List<CompraModel> getAll() {
        return compraDao.obtenerTodo();
    }

    //Se deja como Optional por motivo de error
    //Continuar analizando
    //Consultar en daily
    @GetMapping("/{id}")
    public Optional<CompraModel> getById(@PathVariable Long id) {
        return compraDao.obtenerPorId(id);
    }

    @PostMapping
    public CompraModel create(@RequestBody CompraModel compra) {
        return compraDao.crear(compra);
    }

    @PutMapping("/{id}")
    public CompraModel update(@PathVariable Long id, @RequestBody CompraModel compra) {
        compra.setId(id);
        return compraDao.actualizar(id, compra);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (compraDao.eliminar(id)) {
            return "Item con ID " + id + " eliminado correctamente.";
        } else {
            return "Item con ID " + id + " no encontrado.";
        }
    }

}
