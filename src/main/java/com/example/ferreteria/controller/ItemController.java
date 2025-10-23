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

import com.example.ferreteria.dao.ItemDao;
import com.example.ferreteria.model.ItemModel;

@RestController
@RequestMapping("/item")
public class ItemController{

//-GetAll (Obtener todos los registros) GET
//-GetById (Obtener 1 solo registro por Id) GET
//-Create (Crear un registro) POST
//-Update (Actualizar un registro) PUT
//-Delete (Borrar un registro) DELETE

    @Autowired
    private ItemDao itemDao;

    @GetMapping
    public List<ItemModel> getAll() {
        return itemDao.obtenerTodo();
    }

    //Se deja como Optional por motivo de error
    //Continuar analizando
    //Consultar en daily
    @GetMapping("/{id}")
    public Optional<ItemModel> getById(@PathVariable Long id) {
        return itemDao.obtenerPorId(id);
    }

    @PostMapping
    public ItemModel create(@RequestBody ItemModel item) {
        return itemDao.crear(item);
    }

    @PutMapping("/{id}")
    public ItemModel update(@PathVariable Long id, @RequestBody ItemModel item) {
        item.setId(id);
        return itemDao.actualizar(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (itemDao.eliminar(id)) {
            return "Item con ID " + id + " eliminado correctamente.";
        } else {
            return "Item con ID " + id + " no encontrado.";
        }
    }
    
}