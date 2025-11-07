package com.example.ferreteria.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.dao.OficioDao;
import com.example.ferreteria.model.EspecialistaModel;
import com.example.ferreteria.model.OficioModel;
import com.example.ferreteria.repository.EspecialistaRepository;

@Component
public class EspecialistaSeeder {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    @Autowired
    private OficioDao oficioDao;

    public void seed() {
        if (especialistaRepository.count() > 0) {
            System.out.println("👷 Especialistas ya existen, saltando seeder...");
            return;
        }

        System.out.println("👷 Creando especialistas...");

        // Lista de especialistas con datos realistas
        Object[][] especialistasData = {
            // Nombre, Apellido, Oficio, Disponibilidad, Puntuación (1-5)
            {"Carlos", "González", "Plomero", true, 5},
            {"María", "Rodríguez", "Electricista", true, 4},
            {"Juan", "Pérez", "Carpintero", true, 5},
            {"Ana", "López", "Pintora", false, 4},
            {"Miguel", "Fernández", "Albañil", true, 3},
            {"Laura", "Martín", "Soldadora", true, 5},
            {"Roberto", "Sánchez", "Herrero", true, 4},
            {"Carmen", "García", "Decoradora", false, 4},
            {"Diego", "Morales", "Techista", true, 3},
            {"Patricia", "Ruiz", "Ceramista", true, 4},
            {"Andrés", "Vega", "Plomero", true, 4},
            {"Silvia", "Torres", "Electricista", true, 5},
            {"Fernando", "Ramírez", "Carpintero", false, 3},
            {"Mónica", "Jiménez", "Jardinera", true, 4},
            {"Ricardo", "Castro", "Gasista", true, 5},
            {"Valeria", "Flores", "Pintora", true, 4},
            {"Sebastián", "Herrera", "Soldador", true, 4},
            {"Gabriela", "Mendoza", "Instaladora de Pisos", false, 3},
            {"Martín", "Silva", "Electricista Industrial", true, 5},
            {"Claudia", "Vargas", "Diseñadora de Interiores", true, 4},
            {"Tomás", "Ortega", "Albañil Especializado", true, 4},
            {"Natalia", "Romero", "Plomera Sanitarista", true, 5},
            {"Esteban", "Guerrero", "Carpintero de Obra", false, 3},
            {"Alejandra", "Cruz", "Pintora Decorativa", true, 4},
            {"Nicolás", "Medina", "Soldador Industrial", true, 5},
            {"Lucía", "Paredes", "Instaladora de Drywall", true, 3},
            {"Hernán", "Aguirre", "Techista Especializado", true, 4},
            {"Verónica", "Campos", "Landscaping", false, 4},
            {"Pablo", "Rojas", "Instalador de Gas", true, 5},
            {"Marina", "Acosta", "Electricista Domiciliaria", true, 4}
        };

        int especialistasCreados = 0;
        for (Object[] data : especialistasData) {
            // Buscar el oficio por nombre
            String nombreOficio = (String) data[2];
            OficioModel oficio = oficioDao.obtenerPorNombre(nombreOficio);
            
            if (oficio != null) { // Solo crear si el oficio existe
                EspecialistaModel especialista = new EspecialistaModel();
                especialista.setNombre((String) data[0]);
                especialista.setApellido((String) data[1]);
                especialista.setOficio(oficio);
                especialista.setDisponibilidad((Boolean) data[3]);
                especialista.setPuntuacion((Integer) data[4]);
                especialista.setPerfilImgUrl(null); // Sin imágenes por ahora
                
                especialistaRepository.save(especialista);
                especialistasCreados++;
            }
        }

        System.out.println("✅ Especialistas creados: " + especialistasCreados);
    }
}