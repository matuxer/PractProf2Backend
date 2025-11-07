package com.example.ferreteria.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1) // Se ejecuta después de que Spring Boot haya inicializado completamente
public class DataSeederRunner implements CommandLineRunner {

    @Autowired
    private PaisSeeder paisSeeder;

    @Autowired
    private ProvinciaSeeder provinciaSeeder;

    @Autowired
    private LocalidadSeeder localidadSeeder;

    @Autowired
    private ProductoCategoriaSeeder productoCategoriaSeeder;

    @Autowired
    private TipoServicioSeeder tipoServicioSeeder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n🌱 ===== INICIANDO PROCESO DE SEEDERS =====");
        
        try {
            // Ejecutar seeders en orden de dependencias
            // Primero los datos geográficos
            paisSeeder.seed();
            provinciaSeeder.seed();
            localidadSeeder.seed();
            
            // Luego las categorías y tipos
            productoCategoriaSeeder.seed();
            tipoServicioSeeder.seed();
            
            System.out.println("✅ ===== SEEDERS COMPLETADOS EXITOSAMENTE =====\n");
            
        } catch (Exception e) {
            System.err.println("❌ Error durante la ejecución de seeders: " + e.getMessage());
            e.printStackTrace();
        }
    }
}