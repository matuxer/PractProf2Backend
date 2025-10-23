package com.example.ferreteria.controller;


import com.example.ferreteria.dao.TipoServicioDao;
import com.example.ferreteria.model.TipoServicioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TiposDeServicios")
public class TipoServicioController {

    @Autowired
    private TipoServicioDao tipoServicioDao;

    @GetMapping
    public List<TipoServicioModel> getAll(){
        return tipoServicioDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public TipoServicioModel getById(@PathVariable Long id){
        return tipoServicioDao.obtenerPorId(id);
    }

    @PostMapping
    public TipoServicioModel create(@RequestBody TipoServicioModel tipoServicio){
        return tipoServicioDao.crear(tipoServicio);
    }

    @PutMapping("/{id}")
    public TipoServicioModel update(@PathVariable Long id, @RequestBody TipoServicioModel tipoServicio){
        tipoServicio.setId(id);
        return tipoServicioDao.auctualizar(id,tipoServicio);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        if (tipoServicioDao.eliminar(id)) {
            return "El tipo de servicio con ID " + id + "eliminado correctamente";
        } else {
            return "Tipo de servicio con ID " + id + "no encontrado";
        }
    }
}
