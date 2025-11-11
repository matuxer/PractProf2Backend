package com.example.ferreteria.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.ClienteDao;
import com.example.ferreteria.dao.EspecialistaDao;
import com.example.ferreteria.dao.FeedbackDao;
import com.example.ferreteria.dto.CrearFeedbackRequest;
import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.model.EspecialistaModel;
import com.example.ferreteria.model.FeedbackModel;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private EspecialistaDao especialistaDao;

    // GET para obtener todos los recursos
    @GetMapping
    public List<FeedbackModel> getAll() {
        return feedbackDao.obtenerTodo();
    }

    // GET para obtener un recurso por ID
    @GetMapping("/{id}")
    public FeedbackModel getById(@PathVariable Long id) {
        return feedbackDao.obtenerPorId(id).orElse(null);
    }

    // POST para crear un nuevo recurso
    @PostMapping
    public FeedbackModel create(@RequestBody FeedbackModel feedback) {
        return feedbackDao.crear(feedback);
    }

    // POST para que un cliente cree un feedback sobre un especialista
    @PostMapping("/crear")
    public ResponseEntity<?> crearFeedback(@RequestBody CrearFeedbackRequest request) {
        try {
            // 1. Validar clasificación (1-5)
            if (request.getClasificacion() < 1 || request.getClasificacion() > 5) {
                return ResponseEntity.badRequest().body("La clasificación debe estar entre 1 y 5");
            }

            // 2. Validar que el cliente existe
            ClienteModel cliente = clienteDao.obtenerPorId(request.getClienteId());
            if (cliente == null) {
                return ResponseEntity.badRequest().body("Cliente no encontrado");
            }

            // 3. Validar que el especialista existe
            EspecialistaModel especialista = especialistaDao.obtenerPorId(request.getEspecialistaId()).orElse(null);
            if (especialista == null) {
                return ResponseEntity.badRequest().body("Especialista no encontrado");
            }

            // 4. Crear el feedback
            FeedbackModel feedback = new FeedbackModel();
            feedback.setCliente(cliente);
            feedback.setEspecialista(especialista);
            feedback.setClasificacion(request.getClasificacion());
            feedback.setComentario(request.getComentario());
            feedback.setFecha(LocalDate.now()); // Fecha actual

            // 5. Guardar el feedback
            FeedbackModel feedbackCreado = feedbackDao.crear(feedback);

            // 6. Opcional: Actualizar puntuación promedio del especialista
            // Esto lo puedes implementar si quieres mantener un promedio actualizado
            actualizarPuntuacionEspecialista(especialista);

            return ResponseEntity.ok(feedbackCreado);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el feedback: " + e.getMessage());
        }
    }

    // Método auxiliar para actualizar la puntuación del especialista
    private void actualizarPuntuacionEspecialista(EspecialistaModel especialista) {
        List<FeedbackModel> feedbacks = especialista.getFeedbacksRecibidos();
        if (!feedbacks.isEmpty()) {
            double promedio = feedbacks.stream()
                .mapToInt(FeedbackModel::getClasificacion)
                .average()
                .orElse(0.0);
            
            especialista.setPuntuacion((int) Math.round(promedio));
            especialistaDao.actualizar(especialista.getId(), especialista);
        }
    }

    // PUT para actualizar datos existentes
    @PutMapping("/{id}")
    public FeedbackModel update(@PathVariable Long id, @RequestBody FeedbackModel feedbackActualizado) {
        return feedbackDao.actualizar(id, feedbackActualizado);
    }

    // DELETE para eliminar por ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (feedbackDao.eliminar(id)) {
            return "Feedback con ID " + id + " eliminado correctamente.";
        } else {
            return "Feedback con ID " + id + " no encontrado.";
        }
    }
}

