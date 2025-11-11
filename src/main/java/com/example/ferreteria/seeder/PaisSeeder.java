package com.example.ferreteria.seeder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.PaisModel;
import com.example.ferreteria.repository.PaisRepository;

@Component
public class PaisSeeder {

    @Autowired
    private PaisRepository paisRepository;

    public void seed() {
        if (paisRepository.count() > 0) {
            System.out.println("📍 Países ya existen, saltando seeder...");
            return;
        }

        System.out.println("📍 Creando países...");

        List<String> nombresPaises = Arrays.asList(
            "Argentina",
            "Brasil", 
            "Chile",
            "Uruguay",
            "Paraguay",
            "Bolivia",
            "Perú",
            "Colombia"
        );

        for (String nombrePais : nombresPaises) {
            PaisModel pais = new PaisModel();
            pais.setNombre(nombrePais);
            paisRepository.save(pais);
        }

        System.out.println("✅ Países creados: " + nombresPaises.size());
    }
}