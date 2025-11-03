package com.example.ferreteria.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ItemModel;
import com.example.ferreteria.repository.ItemRepository;
@Repository
public class ItemDao{
    
    @Autowired
    private ItemRepository itemRepository;

    // devuelve todos los registros
    public List<ItemModel> obtenerTodo() {
        return itemRepository.findAll();
    }

    // busca un registro por su ID
    public Optional<ItemModel> obtenerPorId(Long id) {
        return itemRepository.findById(id);
    }

    // guarda un nuevo registro
    public ItemModel crear(ItemModel item) {
        return itemRepository.save(item);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public ItemModel actualizar(Long id, ItemModel itemActualizado) {
        return itemRepository.findById(id).map(item -> {
            item.setPrecio_total(itemActualizado.getPrecio_total());
            return itemRepository.save(item);
        }).orElse(null);
    }

}