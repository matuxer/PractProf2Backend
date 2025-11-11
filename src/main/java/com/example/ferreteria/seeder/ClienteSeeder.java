package com.example.ferreteria.seeder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.model.LocalidadModel;
import com.example.ferreteria.model.PaisModel;
import com.example.ferreteria.model.ProvinciaModel;
import com.example.ferreteria.repository.ClienteRepository;
import com.example.ferreteria.repository.LocalidadRepository;
import com.example.ferreteria.repository.PaisRepository;
import com.example.ferreteria.repository.ProvinciaRepository;

@Component
public class ClienteSeeder {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private LocalidadRepository localidadRepository;

    public void seed() {
        if (clienteRepository.count() > 0) {
            System.out.println("⏭️  Clientes ya existen en la base de datos. Saltando seeder...");
            return;
        }

        System.out.println("🌱 Iniciando seeder de Clientes...");

        // Obtener datos geográficos
        PaisModel argentina = paisRepository.findAll().stream()
                .filter(p -> p.getNombre().equals("Argentina"))
                .findFirst()
                .orElse(null);

        ProvinciaModel buenosAires = provinciaRepository.findAll().stream()
                .filter(p -> p.getNombre().equals("Buenos Aires"))
                .findFirst()
                .orElse(null);

        LocalidadModel caba = localidadRepository.findAll().stream()
                .filter(l -> l.getNombre().equals("CABA"))
                .findFirst()
                .orElse(null);

        // Hash de la contraseña "Test12345"
        String passwordHash = "$2a$10$vUxW7PtEuNi9E2dDnwedO.W4H2yQr1iW27hqJIELWj/32WWyenogq";

        // Crear 10 clientes mock
        ClienteModel cliente1 = new ClienteModel();
        cliente1.setNombre("Juan");
        cliente1.setApellido("Pérez");
        cliente1.setCorreo("juan.perez@example.com");
        cliente1.setContraseña(passwordHash);
        cliente1.setTelefono("1123456789");
        cliente1.setPuntosRecompensa(50);
        cliente1.setDomicilio("Av. Corrientes 1234");
        cliente1.setPais(argentina);
        cliente1.setProvincia(buenosAires);
        cliente1.setLocalidad(caba);

        ClienteModel cliente2 = new ClienteModel();
        cliente2.setNombre("María");
        cliente2.setApellido("González");
        cliente2.setCorreo("maria.gonzalez@example.com");
        cliente2.setContraseña(passwordHash);
        cliente2.setTelefono("1134567890");
        cliente2.setPuntosRecompensa(120);
        cliente2.setDomicilio("Calle Florida 567");
        cliente2.setPais(argentina);
        cliente2.setProvincia(buenosAires);
        cliente2.setLocalidad(caba);

        ClienteModel cliente3 = new ClienteModel();
        cliente3.setNombre("Carlos");
        cliente3.setApellido("Rodríguez");
        cliente3.setCorreo("carlos.rodriguez@example.com");
        cliente3.setContraseña(passwordHash);
        cliente3.setTelefono("1145678901");
        cliente3.setPuntosRecompensa(75);
        cliente3.setDomicilio("Av. Santa Fe 890");
        cliente3.setPais(argentina);
        cliente3.setProvincia(buenosAires);
        cliente3.setLocalidad(caba);

        ClienteModel cliente4 = new ClienteModel();
        cliente4.setNombre("Ana");
        cliente4.setApellido("Martínez");
        cliente4.setCorreo("ana.martinez@example.com");
        cliente4.setContraseña(passwordHash);
        cliente4.setTelefono("1156789012");
        cliente4.setPuntosRecompensa(200);
        cliente4.setDomicilio("Calle Lavalle 234");
        cliente4.setPais(argentina);
        cliente4.setProvincia(buenosAires);
        cliente4.setLocalidad(caba);

        ClienteModel cliente5 = new ClienteModel();
        cliente5.setNombre("Diego");
        cliente5.setApellido("López");
        cliente5.setCorreo("diego.lopez@example.com");
        cliente5.setContraseña(passwordHash);
        cliente5.setTelefono("1167890123");
        cliente5.setPuntosRecompensa(30);
        cliente5.setDomicilio("Av. Rivadavia 456");
        cliente5.setPais(argentina);
        cliente5.setProvincia(buenosAires);
        cliente5.setLocalidad(caba);

        ClienteModel cliente6 = new ClienteModel();
        cliente6.setNombre("Laura");
        cliente6.setApellido("Fernández");
        cliente6.setCorreo("laura.fernandez@example.com");
        cliente6.setContraseña(passwordHash);
        cliente6.setTelefono("1178901234");
        cliente6.setPuntosRecompensa(95);
        cliente6.setDomicilio("Calle Maipú 678");
        cliente6.setPais(argentina);
        cliente6.setProvincia(buenosAires);
        cliente6.setLocalidad(caba);

        ClienteModel cliente7 = new ClienteModel();
        cliente7.setNombre("Roberto");
        cliente7.setApellido("Sánchez");
        cliente7.setCorreo("roberto.sanchez@example.com");
        cliente7.setContraseña(passwordHash);
        cliente7.setTelefono("1189012345");
        cliente7.setPuntosRecompensa(150);
        cliente7.setDomicilio("Av. 9 de Julio 1111");
        cliente7.setPais(argentina);
        cliente7.setProvincia(buenosAires);
        cliente7.setLocalidad(caba);

        ClienteModel cliente8 = new ClienteModel();
        cliente8.setNombre("Sofía");
        cliente8.setApellido("Romero");
        cliente8.setCorreo("sofia.romero@example.com");
        cliente8.setContraseña(passwordHash);
        cliente8.setTelefono("1190123456");
        cliente8.setPuntosRecompensa(60);
        cliente8.setDomicilio("Calle Reconquista 222");
        cliente8.setPais(argentina);
        cliente8.setProvincia(buenosAires);
        cliente8.setLocalidad(caba);

        ClienteModel cliente9 = new ClienteModel();
        cliente9.setNombre("Martín");
        cliente9.setApellido("Torres");
        cliente9.setCorreo("martin.torres@example.com");
        cliente9.setContraseña(passwordHash);
        cliente9.setTelefono("1101234567");
        cliente9.setPuntosRecompensa(180);
        cliente9.setDomicilio("Av. Callao 333");
        cliente9.setPais(argentina);
        cliente9.setProvincia(buenosAires);
        cliente9.setLocalidad(caba);

        ClienteModel cliente10 = new ClienteModel();
        cliente10.setNombre("Valentina");
        cliente10.setApellido("Díaz");
        cliente10.setCorreo("valentina.diaz@example.com");
        cliente10.setContraseña(passwordHash);
        cliente10.setTelefono("1112345678");
        cliente10.setPuntosRecompensa(110);
        cliente10.setDomicilio("Calle Pellegrini 444");
        cliente10.setPais(argentina);
        cliente10.setProvincia(buenosAires);
        cliente10.setLocalidad(caba);

        // Guardar todos los clientes
        clienteRepository.saveAll(List.of(
            cliente1, cliente2, cliente3, cliente4, cliente5,
            cliente6, cliente7, cliente8, cliente9, cliente10
        ));

        System.out.println("✅ 10 clientes creados exitosamente");
        System.out.println("📝 Contraseña para todos: Test12345");
    }
}
