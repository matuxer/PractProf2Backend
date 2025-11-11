package com.example.ferreteria.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads/productos/";

    @PostMapping("/imagen")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile file) {
        return uploadImageToDirectory(file, UPLOAD_DIR, "/uploads/productos/");
    }

    @PostMapping("/perfil")
    public ResponseEntity<Map<String, String>> uploadProfileImage(@RequestParam("file") MultipartFile file) {
        String perfilDir = "uploads/especialistas/";
        return uploadImageToDirectory(file, perfilDir, "/uploads/especialistas/");
    }

    private ResponseEntity<Map<String, String>> uploadImageToDirectory(MultipartFile file, String uploadDir, String urlPrefix) {
        Map<String, String> response = new HashMap<>();
        
        try {
            // Validar que el archivo no esté vacío
            if (file.isEmpty()) {
                response.put("error", "El archivo está vacío");
                return ResponseEntity.badRequest().body(response);
            }

            // Validar tipo de archivo (solo imágenes)
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                response.put("error", "El archivo debe ser una imagen");
                return ResponseEntity.badRequest().body(response);
            }

            // Crear directorio si no existe
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            // Generar nombre único para el archivo
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + extension;

            // Guardar archivo
            File destFile = new File(uploadDirectory, fileName);
            file.transferTo(destFile);

            // Construir URL de acceso
            String imageUrl = urlPrefix + fileName;
            
            response.put("message", "Imagen subida exitosamente");
            response.put("url", imageUrl);
            response.put("fileName", fileName);
            
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            response.put("error", "Error al guardar el archivo: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}