package com.example.ferreteria.dao;

import com.example.ferreteria.model.PaisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.ferreteria.repository.PaisRepository;

import java.util.List;

@Repository
public class PaisDAO {

    @Autowired
    private PaisRepository paisRepository;

    public List<PaisModel> obtenerTodo(){
        return paisRepository.findAll();
    }

    public PaisModel obtenerPorId(Long id){
        return paisRepository.findById(id).orElse(null);
    }

    public PaisModel crear(PaisModel paisModel){
        return paisRepository.save(paisModel);
    }


    public boolean eliminar(Long id){
        if (paisRepository.existsById(id)){
            paisRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public PaisModel actualizar(Long id, PaisModel paisAcrualizado){
        return paisRepository.findById(id).map(pais ->{
            pais.setNombre(paisAcrualizado.getNombre());
            return paisRepository.save(pais);
        }).orElse(null);
    }


}
