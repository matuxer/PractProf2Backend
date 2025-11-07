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

        // Lista de especialistas con datos realistas e imágenes
        Object[][] especialistasData = {
            // Nombre, Apellido, Oficio, Disponibilidad, Puntuación (1-5), Imagen
            {"Carlos", "González", "Plomero", true, 5, "Carlos_González.png"},
            {"María", "Rodríguez", "Electricista", true, 4, "María_Rodríguez.png"},
            {"Juan", "Pérez", "Carpintero", true, 5, "Juan_Pérez.png"},
            {"Ana", "López", "Pintor", false, 4, "Ana_López.png"},
            {"Miguel", "Fernández", "Albañil", true, 3, "Miguel_Fernández.png"},
            {"Laura", "Martín", "Soldador", true, 5, "Laura_Martín.png"},
            {"Roberto", "Sánchez", "Herrero", true, 4, "Roberto_Sánchez.png"},
            {"Carmen", "García", "Decorador", false, 4, "Carmen_García.png"},
            {"Diego", "Morales", "Techista", true, 3, "Diego_Morales.png"},
            {"Patricia", "Ruiz", "Ceramista", true, 4, "Patricia_Ruiz.png"},
            {"Andrés", "Vega", "Plomero", true, 4, "Andrés_Vega.png"},
            {"Silvia", "Torres", "Electricista", true, 5, "Silvia_Torres.png"},
            {"Fernando", "Ramírez", "Carpintero", false, 3, "Fernando_Ramírez.png"},
            {"Mónica", "Jiménez", "Jardinero", true, 4, "Mónica_Jiménez.png"},
            {"Ricardo", "Castro", "Gasista", true, 5, "Ricardo_Castro.png"},
            {"Valeria", "Flores", "Pintor", true, 4, "Valeria_Flores.png"},
            {"Sebastián", "Herrera", "Soldador", true, 4, "Sebastián_Herrera.png"},
            {"Gabriela", "Mendoza", "Instalador de Drywall", false, 3, "Gabriela_Mendoza.png"},
            {"Martín", "Silva", "Electricista Industrial", true, 5, "Martín_Silva.png"},
            {"Claudia", "Vargas", "Diseñador de Interiores", true, 4, "Claudia_Vargas.png"},
            {"Tomás", "Ortega", "Albañil Especializado", true, 4, "Tomás_Ortega.png"},
            {"Natalia", "Romero", "Plomero Sanitarista", true, 5, "Natalia_Romero.png"},
            {"Esteban", "Guerrero", "Carpintero de Obra", false, 3, "Esteban_Guerrero.png"},
            {"Alejandra", "Cruz", "Pintor Decorativo", true, 4, "Alejandra_Cruz.png"},
            {"Nicolás", "Medina", "Soldador Industrial", true, 5, "Nicolás_Medina.png"},
            {"Lucía", "Paredes", "Instalador de Drywall", true, 3, "Lucía_Paredes.png"},
            {"Hernán", "Aguirre", "Techista Especializado", true, 4, "Hernán_Aguirre.png"},
            {"Verónica", "Campos", "Landscaping", false, 4, "Verónica_Campos.png"},
            {"Pablo", "Rojas", "Instalador de Gas", true, 5, "Pablo_Rojas.png"},
            {"Marina", "Acosta", "Electricista Domiciliario", true, 4, "Marina_Acosta.png"}
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
                especialista.setPerfilImgUrl("/uploads/especialistas/perfil/" + (String) data[5]);
                
                especialistaRepository.save(especialista);
                especialistasCreados++;
            }
        }

        System.out.println("✅ Especialistas creados: " + especialistasCreados);
    }
}