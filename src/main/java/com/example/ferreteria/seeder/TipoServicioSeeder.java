package com.example.ferreteria.seeder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.TipoServicioModel;
import com.example.ferreteria.repository.TipoServicioRepository;

@Component
public class TipoServicioSeeder {

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    public void seed() {
        if (tipoServicioRepository.count() > 0) {
            System.out.println("🔧 Tipos de servicios ya existen, saltando seeder...");
            return;
        }

        System.out.println("🔧 Creando tipos de servicios...");

        List<String> nombresTipoServicios = Arrays.asList(
            "Plomería",
            "Electricidad",
            "Carpintería",
            "Albañilería",
            "Pintura",
            "Herrería",
            "Jardinería",
            "Techado",
            "Climatización",
            "Cerrajería",
            "Vidriería",
            "Instalación de Pisos",
            "Instalación de Cerámicos",
            "Reparación de Electrodomésticos",
            "Instalación de Gas",
            "Soldadura",
            "Fumigación",
            "Limpieza de Tanques",
            "Mantenimiento General"
        );

        for (String nombreTipoServicio : nombresTipoServicios) {
            TipoServicioModel tipoServicio = new TipoServicioModel();
            tipoServicio.setNombre(nombreTipoServicio);
            tipoServicioRepository.save(tipoServicio);
        }

        System.out.println("✅ Tipos de servicios creados: " + nombresTipoServicios.size());
    }
}