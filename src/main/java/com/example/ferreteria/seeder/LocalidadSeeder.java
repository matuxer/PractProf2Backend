package com.example.ferreteria.seeder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.LocalidadModel;
import com.example.ferreteria.repository.LocalidadRepository;

@Component
public class LocalidadSeeder {

    @Autowired
    private LocalidadRepository localidadRepository;

    public void seed() {
        if (localidadRepository.count() > 0) {
            System.out.println("🏘️ Localidades ya existen, saltando seeder...");
            return;
        }

        System.out.println("🏘️ Creando localidades principales...");

        List<String> nombresLocalidades = Arrays.asList(
            // Buenos Aires
            "La Plata",
            "Mar del Plata",
            "Bahía Blanca",
            "Tandil",
            "Olavarría",
            "Pergamino",
            "Junín",
            "Mercedes",
            "Luján",
            "San Nicolás",
            "Quilmes",
            "Avellaneda",
            "Lanús",
            
            // Córdoba
            "Córdoba Capital",
            "Villa María",
            "Río Cuarto",
            "Villa Carlos Paz",
            
            // Santa Fe
            "Rosario",
            "Santa Fe Capital",
            "Reconquista",
            "Rafaela",
            
            // Mendoza
            "Mendoza Capital",
            "San Rafael",
            "Godoy Cruz",
            
            // Otras provincias
            "Tucumán Capital",
            "Salta Capital",
            "Corrientes Capital",
            "Posadas",
            "Paraná",
            "Resistencia"
        );

        for (String nombreLocalidad : nombresLocalidades) {
            LocalidadModel localidad = new LocalidadModel(nombreLocalidad);
            localidadRepository.save(localidad);
        }

        System.out.println("✅ Localidades creadas: " + nombresLocalidades.size());
    }
}