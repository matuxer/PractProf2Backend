package com.example.ferreteria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.FeedbackModel;
import com.example.ferreteria.repository.FeedbackRepository;

@Repository
public class FeedbackDao {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Devuelve todos los registros
    public List<FeedbackModel> obtenerTodo() {
        return feedbackRepository.findAll();
    }

    // Busca un registro por su ID
    public Optional<FeedbackModel> obtenerPorId(Long id) {
        return feedbackRepository.findById(id);
    }

    // Guarda un nuevo registro
    public FeedbackModel crear(FeedbackModel feedback) {
        return feedbackRepository.save(feedback);
    }

    // Borra un registro por ID
    public boolean eliminar(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Modifica un registro existente
    public FeedbackModel actualizar(Long id, FeedbackModel feedbackActualizado) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setFecha(feedbackActualizado.getFecha());
            feedback.setClasificacion(feedbackActualizado.getClasificacion());
            feedback.setComentario(feedbackActualizado.getComentario());
            return feedbackRepository.save(feedback);
        }).orElse(null);
    }
}

