package com.example.ferreteria.controller;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.ItemDao;

@RestController
public class ItemController implements ItemDao {

//-GetAll (Obtener todos los registros) GET
//-GetById (Obtener 1 solo registro por Id) GET
//-Create (Crear un registro) POST
//-Update (Actualizar un registro) PUT
//-Delete (Borrar un registro) DELETE

    @Autowired
    private ItemDao itemDao;

    @Override
    @RequestMapping(value="item")
    public List<Item> obtenerTodo() {
        List <Item> item = ItemDao.getItem();
        return item;
    }

    @Override
    @RequestMapping(value="item/{id}")
    public List<Item> obtenerPorId(int id) {
        List <Item> item = ItemDao.getItem(id);
        return item;
    }

    public void crear(@RequestBody Item item) {
        itemDao.save(item);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable("id")int id){
        itemDao.deleteById(id);
    }

    public void actualizar(@RequestBody Item item) {
        itemDao.save(item);
    }
    
}