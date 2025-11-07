package com.example.ferreteria.seeder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.ProvinciaModel;
import com.example.ferreteria.repository.ProvinciaRepository;

@Component
public class ProvinciaSeeder {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void seed() {
        if (provinciaRepository.count() > 0) {
            System.out.println("🏛️ Provincias ya existen, saltando seeder...");
            return;
        }

        System.out.println("🏛️ Creando provincias de Argentina...");

        List<String> nombresProvincias = Arrays.asList(
            "Buenos Aires",
            "Córdoba",
            "Santa Fe", 
            "Mendoza",
            "San Luis",
            "Entre Ríos",
            "Corrientes",
            "Misiones",
            "Tucumán",
            "Salta",
            "Chaco",
            "Santiago del Estero",
            "Formosa",
            "Jujuy",
            "Catamarca",
            "La Rioja",
            "San Juan",
            "Neuquén",
            "Río Negro",
            "Chubut",
            "Santa Cruz",
            "Tierra del Fuego",
            "La Pampa",
            "Ciudad Autónoma de Buenos Aires"
        );

        for (String nombreProvincia : nombresProvincias) {
            ProvinciaModel provincia = new ProvinciaModel(nombreProvincia);
            provinciaRepository.save(provincia);
        }

        System.out.println("✅ Provincias creadas: " + nombresProvincias.size());
    }
}