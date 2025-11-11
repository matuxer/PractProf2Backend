package com.example.ferreteria.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.dao.OficioDao;
import com.example.ferreteria.model.OficioModel;

@Component
public class OficioSeeder {

    @Autowired
    private OficioDao oficioDao;

    public void seed() {
        if (oficioDao.contar() > 0) {
            System.out.println("🏗️ Oficios ya existen, saltando seeder...");
            return;
        }

        System.out.println("🏗️ Creando oficios...");

        // Lista de oficios con sus categorías
        Object[][] oficiosData = {
            // Nombre, Descripción, Categoría
            {"Plomero", "Especialista en instalación y reparación de sistemas de agua y desagües", "Construcción"},
            {"Electricista", "Técnico en instalaciones eléctricas residenciales y comerciales", "Servicios Técnicos"},
            {"Carpintero", "Artesano especializado en trabajos con madera", "Construcción"},
            {"Pintor", "Especialista en pintura y acabados decorativos", "Acabados"},
            {"Albañil", "Constructor especializado en mampostería y estructuras", "Construcción"},
            {"Soldador", "Técnico en soldadura y trabajos con metales", "Metalurgia"},
            {"Herrero", "Artesano especializado en forja y trabajos con hierro", "Metalurgia"},
            {"Decorador", "Especialista en diseño y decoración de interiores", "Diseño"},
            {"Techista", "Especialista en construcción y reparación de techos", "Construcción"},
            {"Ceramista", "Experto en colocación de cerámicos y revestimientos", "Acabados"},
            {"Gasista", "Técnico en instalaciones de gas natural y GLP", "Servicios Técnicos"},
            {"Jardinero", "Especialista en diseño y mantenimiento de jardines", "Paisajismo"},
            {"Electricista Industrial", "Técnico en sistemas eléctricos industriales de alta tensión", "Servicios Técnicos"},
            {"Diseñador de Interiores", "Profesional en diseño y ambientación de espacios", "Diseño"},
            {"Albañil Especializado", "Constructor experto en técnicas avanzadas de construcción", "Construcción"},
            {"Plomero Sanitarista", "Especialista en sistemas sanitarios y tratamiento de agua", "Construcción"},
            {"Carpintero de Obra", "Carpintero especializado en estructuras y encofrados", "Construcción"},
            {"Pintor Decorativo", "Artista especializado en técnicas decorativas y murales", "Acabados"},
            {"Soldador Industrial", "Técnico en soldadura de alta precisión para industria", "Metalurgia"},
            {"Instalador de Drywall", "Especialista en sistemas de construcción en seco", "Construcción"},
            {"Techista Especializado", "Experto en sistemas de techado y impermeabilización", "Construcción"},
            {"Landscaping", "Diseñador y constructor de paisajes y espacios verdes", "Paisajismo"},
            {"Instalador de Gas", "Técnico certificado en instalaciones de gas domiciliarias", "Servicios Técnicos"},
            {"Electricista Domiciliario", "Especialista en instalaciones eléctricas residenciales", "Servicios Técnicos"}
        };

        int oficiosCreados = 0;
        for (Object[] data : oficiosData) {
            OficioModel oficio = new OficioModel();
            oficio.setNombre((String) data[0]);
            oficio.setDescripcion((String) data[1]);
            oficio.setCategoria((String) data[2]);
            
            oficioDao.crear(oficio);
            oficiosCreados++;
        }

        System.out.println("✅ Oficios creados: " + oficiosCreados);
    }
}