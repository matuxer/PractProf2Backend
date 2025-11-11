package com.example.ferreteria.seeder;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.model.EspecialistaModel;
import com.example.ferreteria.model.FeedbackModel;
import com.example.ferreteria.repository.ClienteRepository;
import com.example.ferreteria.repository.EspecialistaRepository;
import com.example.ferreteria.repository.FeedbackRepository;

@Component
public class FeedbackSeeder {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EspecialistaRepository especialistaRepository;

    public void seed() {
        if (feedbackRepository.count() > 0) {
            System.out.println("⏭️  Feedbacks ya existen en la base de datos. Saltando seeder...");
            return;
        }

        System.out.println("🌱 Iniciando seeder de Feedbacks...");

        List<ClienteModel> clientes = clienteRepository.findAll();
        List<EspecialistaModel> especialistas = especialistaRepository.findAll();

        if (clientes.isEmpty() || especialistas.isEmpty()) {
            System.out.println("⚠️  No hay clientes o especialistas en la base de datos. Saltando seeder de feedbacks...");
            return;
        }

        Random random = new Random();
        
        // Comentarios variados para feedbacks
        String[] comentariosPositivos = {
            "Excelente trabajo, muy profesional y puntual",
            "Muy satisfecho con el servicio prestado",
            "Recomendable 100%, cumplió con todas mis expectativas",
            "Trabajo de calidad, lo volvería a contratar",
            "Muy buena atención y excelente resultado final"
        };

        String[] comentariosNeutrales = {
            "Buen trabajo en general, aunque tardó un poco más de lo esperado",
            "El resultado fue aceptable, cumplió lo acordado",
            "Servicio correcto, sin mayores inconvenientes"
        };

        String[] comentariosNegativos = {
            "El trabajo estuvo bien pero esperaba un poco más de prolijidad",
            "Aceptable pero no cumplió del todo mis expectativas"
        };

        int feedbacksCreados = 0;

        // Crear entre 3 y 7 feedbacks por especialista
        for (EspecialistaModel especialista : especialistas) {
            int cantidadFeedbacks = random.nextInt(5) + 3; // Entre 3 y 7 feedbacks
            int sumaClasificaciones = 0;
            
            for (int i = 0; i < cantidadFeedbacks; i++) {
                FeedbackModel feedback = new FeedbackModel();
                
                // Cliente aleatorio
                ClienteModel clienteAleatorio = clientes.get(random.nextInt(clientes.size()));
                feedback.setCliente(clienteAleatorio);
                feedback.setEspecialista(especialista);
                
                // Clasificación con tendencia a ser buena (más peso en 4-5 estrellas)
                int clasificacion;
                int rand = random.nextInt(100);
                if (rand < 50) {
                    clasificacion = 5; // 50% probabilidad de 5 estrellas
                } else if (rand < 80) {
                    clasificacion = 4; // 30% probabilidad de 4 estrellas
                } else if (rand < 95) {
                    clasificacion = 3; // 15% probabilidad de 3 estrellas
                } else {
                    clasificacion = 2; // 5% probabilidad de 2 estrellas
                }
                feedback.setClasificacion(clasificacion);
                sumaClasificaciones += clasificacion;
                
                // Comentario según la clasificación
                String comentario;
                if (clasificacion >= 4) {
                    comentario = comentariosPositivos[random.nextInt(comentariosPositivos.length)];
                } else if (clasificacion == 3) {
                    comentario = comentariosNeutrales[random.nextInt(comentariosNeutrales.length)];
                } else {
                    comentario = comentariosNegativos[random.nextInt(comentariosNegativos.length)];
                }
                feedback.setComentario(comentario);
                
                // Fecha aleatoria en los últimos 60 días
                int diasAtras = random.nextInt(60);
                feedback.setFecha(LocalDate.now().minusDays(diasAtras));
                
                feedbackRepository.save(feedback);
                feedbacksCreados++;
            }
            
            // Actualizar puntuación promedio del especialista
            double promedio = (double) sumaClasificaciones / cantidadFeedbacks;
            especialista.setPuntuacion((int) Math.round(promedio));
            especialistaRepository.save(especialista);
        }

        System.out.println("✅ " + feedbacksCreados + " feedbacks creados exitosamente");
        System.out.println("✅ Puntuaciones de especialistas actualizadas");
    }
}
