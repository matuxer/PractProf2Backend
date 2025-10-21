package com.example.ferreteria.controller;

import com.example.ferreteria.dao.PaisDAO;
import com.example.ferreteria.model.PaisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")

public class PaisController {

    @Autowired
    private PaisDAO paisDAO;

    @GetMapping
    public List<PaisModel> getAll(){
        return paisDAO.obtenerTodo();
    }

    @GetMapping("/{id}")
    public PaisModel getById(@PathVariable Long id){
        return paisDAO.obtenerPorId(id);
    }

    @PostMapping
    public PaisModel create(@RequestBody PaisModel pais){
        return paisDAO.crear(pais);
    }

    @PutMapping("/{id}")
    public PaisModel update(@PathVariable Long id ,@RequestBody PaisModel pais){
        pais.setId(id);
        return paisDAO.actualizar(id, pais);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        if (paisDAO.eliminar(id)){
            return "Pais con ID " + id + "eliminado correctamente";
        }else{
            return "Pais con ID " + id + "no encontrado";
        }
    }
}