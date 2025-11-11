package com.example.ferreteria.config;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileConfig implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Crear directorios de uploads al iniciar la aplicación
        File uploadsDir = new File("uploads");
        File productosDir = new File("uploads/productos");
        File especialistasDir = new File("uploads/especialistas");
        
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs();
            System.out.println("Directorio 'uploads' creado");
        }
        
        if (!productosDir.exists()) {
            productosDir.mkdirs();
            System.out.println("Directorio 'uploads/productos' creado");
        }
        
        if (!especialistasDir.exists()) {
            especialistasDir.mkdirs();
            System.out.println("Directorio 'uploads/especialistas' creado");
        }
    }
}