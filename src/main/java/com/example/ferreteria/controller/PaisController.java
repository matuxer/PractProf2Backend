package com.example.ferreteria.controller;

import com.example.ferreteria.dao.PaisDao;
import com.example.ferreteria.model.PaisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")

public class PaisController {

    @Autowired
    private PaisDao paisDao;

    @GetMapping
    public List<PaisModel> getAll(){
        return paisDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public PaisModel getById(@PathVariable Long id){
        return paisDao.obtenerPorId(id);
    }

    @PostMapping
    public PaisModel create(@RequestBody PaisModel pais){
        return paisDao.crear(pais);
    }

    @PutMapping("/{id}")
    public PaisModel update(@PathVariable Long id ,@RequestBody PaisModel pais){
        pais.setId(id);
        return paisDao.actualizar(id, pais);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        if (paisDao.eliminar(id)){
            return "Pais con ID " + id + "eliminado correctamente";
        }else{
            return "Pais con ID " + id + "no encontrado";
        }
    }
}