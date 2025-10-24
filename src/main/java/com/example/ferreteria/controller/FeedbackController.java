package com.example.ferreteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.FeedbackDao;
import com.example.ferreteria.model.FeedbackModel;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackDao feedbackDao;

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

