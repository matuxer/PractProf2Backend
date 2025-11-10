package com.example.ferreteria.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.EspecialistaModel;
import com.example.ferreteria.model.FeedbackModel;
import com.example.ferreteria.repository.EspecialistaRepository;
import com.example.ferreteria.repository.FeedbackRepository;

@Repository
public class FeedbackDao {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EspecialistaRepository especialistaRepository;

    // Devuelve todos los registros
    public List<FeedbackModel> obtenerTodo() {
        return feedbackRepository.findAll();
    }

    // Busca un registro por su ID
    public Optional<FeedbackModel> obtenerPorId(Long id) {
        return feedbackRepository.findById(id);
    }

    /**
     * Guarda un nuevo feedback y actualiza automáticamente la puntuación del especialista
     * La puntuación se calcula como el promedio de todos los feedbacks del especialista
     */
    public FeedbackModel crear(FeedbackModel feedback) {
        // Guardar el feedback
        FeedbackModel feedbackGuardado = feedbackRepository.save(feedback);
        
        // Actualizar la puntuación del especialista
        if (feedback.getEspecialista() != null) {
            actualizarPuntuacionEspecialista(feedback.getEspecialista().getId());
        }
        
        return feedbackGuardado;
    }

    // Borra un registro por ID
    public boolean eliminar(Long id) {
        Optional<FeedbackModel> feedbackOpt = feedbackRepository.findById(id);
        
        if (feedbackOpt.isPresent()) {
            FeedbackModel feedback = feedbackOpt.get();
            Long especialistaId = feedback.getEspecialista() != null ? feedback.getEspecialista().getId() : null;
            
            feedbackRepository.deleteById(id);
            
            // Actualizar puntuación del especialista después de eliminar el feedback
            if (especialistaId != null) {
                actualizarPuntuacionEspecialista(especialistaId);
            }
            
            return true;
        }
        return false;
    }

    /**
     * Modifica un feedback existente y actualiza la puntuación del especialista
     */
    public FeedbackModel actualizar(Long id, FeedbackModel feedbackActualizado) {
        return feedbackRepository.findById(id).map(feedback -> {
            feedback.setFecha(feedbackActualizado.getFecha());
            feedback.setClasificacion(feedbackActualizado.getClasificacion());
            feedback.setComentario(feedbackActualizado.getComentario());
            
            FeedbackModel feedbackGuardado = feedbackRepository.save(feedback);
            
            // Actualizar puntuación del especialista después de modificar el feedback
            if (feedback.getEspecialista() != null) {
                actualizarPuntuacionEspecialista(feedback.getEspecialista().getId());
            }
            
            return feedbackGuardado;
        }).orElse(null);
    }

    /**
     * Calcula y actualiza la puntuación de un especialista basándose en el promedio
     * de todos sus feedbacks
     * @param especialistaId ID del especialista a actualizar
     */
    private void actualizarPuntuacionEspecialista(Long especialistaId) {
        Optional<EspecialistaModel> especialistaOpt = especialistaRepository.findById(especialistaId);
        
        if (especialistaOpt.isPresent()) {
            EspecialistaModel especialista = especialistaOpt.get();
            List<FeedbackModel> feedbacks = especialista.getFeedbacksRecibidos();
            
            if (feedbacks != null && !feedbacks.isEmpty()) {
                // Calcular promedio de clasificaciones
                double promedio = feedbacks.stream()
                    .mapToInt(FeedbackModel::getClasificacion)
                    .average()
                    .orElse(0.0);
                
                // Redondear al entero más cercano
                int puntuacion = (int) Math.round(promedio);
                
                // Actualizar puntuación del especialista
                especialista.setPuntuacion(puntuacion);
                especialistaRepository.save(especialista);
            } else {
                // Si no tiene feedbacks, puntuación en 0
                especialista.setPuntuacion(0);
                especialistaRepository.save(especialista);
            }
        }
    }
}

