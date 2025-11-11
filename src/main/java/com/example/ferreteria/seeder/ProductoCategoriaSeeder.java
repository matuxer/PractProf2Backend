package com.example.ferreteria.seeder;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.ProductoCategoriaModel;
import com.example.ferreteria.repository.ProductoCategoriaRepository;

@Component
public class ProductoCategoriaSeeder {

    @Autowired
    private ProductoCategoriaRepository productoCategoriaRepository;

    public void seed() {
        if (productoCategoriaRepository.count() > 0) {
            System.out.println("🏷️ Categorías de productos ya existen, saltando seeder...");
            return;
        }

        System.out.println("🏷️ Creando categorías de productos...");

        List<String> nombresCategorias = Arrays.asList(
            "Herramientas Manuales",
            "Herramientas Eléctricas",
            "Materiales de Construcción",
            "Plomería",
            "Electricidad",
            "Pinturería",
            "Ferretería Industrial",
            "Jardinería",
            "Seguridad",
            "Tornillería",
            "Adhesivos y Selladores",
            "Cables y Alambres",
            "Iluminación",
            "Cerrajería",
            "Herrajes",
            "Abrasivos",
            "Soldadura",
            "Climatización"
        );

        for (String nombreCategoria : nombresCategorias) {
            ProductoCategoriaModel categoria = new ProductoCategoriaModel();
            categoria.setNombre(nombreCategoria);
            productoCategoriaRepository.save(categoria);
        }

        System.out.println("✅ Categorías de productos creadas: " + nombresCategorias.size());
    }
}