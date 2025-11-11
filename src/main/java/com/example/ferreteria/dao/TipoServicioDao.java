package com.example.ferreteria.dao;


import com.example.ferreteria.model.TipoServicioModel;
import com.example.ferreteria.repository.TipoServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoServicioDao {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public List<TipoServicioModel> obtenerTodo(){
        return tipoServicioRepository.findAll();
    }

    public TipoServicioModel obtenerPorId(Long id){
        return tipoServicioRepository.findById(id).orElse(null);
    }

    public TipoServicioModel crear(TipoServicioModel tipoServicio){
        return tipoServicioRepository.save(tipoServicio);
    }

    public boolean eliminar(Long id){
        if (tipoServicioRepository.existsById(id)){
            tipoServicioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public TipoServicioModel auctualizar(Long id, TipoServicioModel tipoServicioActualizado){
        return tipoServicioRepository.findById(id).map(tipoServicio -> {
            tipoServicio.setNombre(tipoServicioActualizado.getNombre());
            tipoServicio.setServicios(tipoServicioActualizado.getServicios());
            return tipoServicioRepository.save(tipoServicio);
        }).orElse(null);

    }

}
