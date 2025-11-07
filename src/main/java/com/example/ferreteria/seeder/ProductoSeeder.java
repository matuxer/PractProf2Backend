package com.example.ferreteria.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.model.ProductoModel;
import com.example.ferreteria.model.ProductoCategoriaModel;
import com.example.ferreteria.repository.ProductoRepository;
import com.example.ferreteria.repository.ProductoCategoriaRepository;

@Component
public class ProductoSeeder {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoCategoriaRepository productoCategoriaRepository;

    public void seed() {
        if (productoRepository.count() > 0) {
            System.out.println("🛠️ Productos ya existen, saltando seeder...");
            return;
        }

        System.out.println("🛠️ Creando productos...");

        // Obtener categorías
        ProductoCategoriaModel herramientasManuales = productoCategoriaRepository.findByNombre("Herramientas Manuales").orElse(null);
        ProductoCategoriaModel herramientasElectricas = productoCategoriaRepository.findByNombre("Herramientas Eléctricas").orElse(null);
        ProductoCategoriaModel materialesConstruccion = productoCategoriaRepository.findByNombre("Materiales de Construcción").orElse(null);
        ProductoCategoriaModel plomeria = productoCategoriaRepository.findByNombre("Plomería").orElse(null);
        ProductoCategoriaModel electricidad = productoCategoriaRepository.findByNombre("Electricidad").orElse(null);
        ProductoCategoriaModel pintureria = productoCategoriaRepository.findByNombre("Pinturería").orElse(null);
        ProductoCategoriaModel tornilleria = productoCategoriaRepository.findByNombre("Tornillería").orElse(null);
        ProductoCategoriaModel cerrajeria = productoCategoriaRepository.findByNombre("Cerrajería").orElse(null);
        ProductoCategoriaModel soldadura = productoCategoriaRepository.findByNombre("Soldadura").orElse(null);
        ProductoCategoriaModel seguridad = productoCategoriaRepository.findByNombre("Seguridad").orElse(null);

        // Lista de productos con detalles realistas (sin imágenes por ahora)
        Object[][] productosData = {
            // Herramientas Manuales
            {"Martillo Carpintero 16oz", "Martillo de carpintero con mango de fibra de vidrio, cabeza forjada", 50, 15.99f, herramientasManuales},
            {"Destornillador Phillips #2", "Destornillador Phillips punta #2, mango ergonómico antideslizante", 100, 8.50f, herramientasManuales},
            {"Alicate Universal 8\"", "Alicate universal 8 pulgadas, acero templado, mango aislado", 75, 22.75f, herramientasManuales},
            {"Llave Inglesa 10\"", "Llave inglesa ajustable 10 pulgadas, acero al carbono", 60, 18.90f, herramientasManuales},
            {"Sierra de Costilla 14\"", "Sierra de costilla 14 pulgadas, dientes templados, mango de madera", 40, 28.50f, herramientasManuales},
            
            // Herramientas Eléctricas
            {"Taladro Eléctrico 600W", "Taladro eléctrico 600W con percutor, mandril 13mm, cable 2m", 25, 89.99f, herramientasElectricas},
            {"Amoladora Angular 4.5\"", "Amoladora angular 4.5 pulgadas, 750W, 11000 RPM, mango auxiliar", 30, 67.50f, herramientasElectricas},
            {"Caladora 450W", "Caladora eléctrica 450W, corte pendular, base ajustable", 20, 75.90f, herramientasElectricas},
            {"Lijadora Orbital", "Lijadora orbital eléctrica 200W, base velcro, recolector de polvo", 18, 58.75f, herramientasElectricas},
            
            // Materiales de Construcción
            {"Cemento Portland x 50kg", "Cemento Portland tipo I, bolsa 50kg, resistencia 42.5 MPa", 200, 12.50f, materialesConstruccion},
            {"Ladrillo Común x 100", "Ladrillos comunes rojos, medidas estándar 6x12x25cm, pack x100", 150, 35.00f, materialesConstruccion},
            {"Arena Gruesa x m³", "Arena gruesa para construcción, lavada y clasificada, por metro cúbico", 80, 25.80f, materialesConstruccion},
            {"Hierro 8mm x 12m", "Hierro construcción ADN420 diámetro 8mm, barra 12 metros", 120, 18.90f, materialesConstruccion},
            
            // Plomería
            {"Caño PVC 110mm x 3m", "Caño PVC desagüe 110mm x 3 metros, norma IRAM", 90, 22.40f, plomeria},
            {"Grifo Monocomando Cocina", "Grifo monocomando para cocina, latón cromado, pico giratorio", 35, 125.00f, plomeria},
            {"Codo PVC 90° 50mm", "Codo PVC 90 grados diámetro 50mm para desagüe cloacal", 200, 3.75f, plomeria},
            {"Sifón Lavatorio Cromado", "Sifón para lavatorio cromado, 1.1/4 pulgadas con tapón", 45, 28.90f, plomeria},
            
            // Electricidad
            {"Cable TPR 2.5mm x 100m", "Cable TPR 2.5mm² x 100 metros, aislación termoplástica", 25, 89.50f, electricidad},
            {"Interruptor Simple Blanco", "Interruptor simple 10A, blanco, norma IRAM 2071", 150, 8.75f, electricidad},
            {"Toma Corriente 10A", "Toma corriente 10A con puesta a tierra, blanco, norma IRAM", 120, 12.30f, electricidad},
            {"Lámpara LED 12W", "Lámpara LED 12W luz fría 6500K, rosca E27, vida útil 25000hs", 80, 18.50f, electricidad},
            
            // Pinturería
            {"Pintura Látex Interior 4L", "Pintura látex acrílico interior blanco mate, 4 litros, lavable", 60, 32.90f, pintureria},
            {"Rodillo Lana 23cm", "Rodillo de lana natural 23cm, mango plástico ergonómico", 100, 15.75f, pintureria},
            {"Pincel Cerda 2\"", "Pincel cerda natural 2 pulgadas, virola inoxidable, mango madera", 80, 12.40f, pintureria},
            {"Enduido Plástico x 30kg", "Enduido plástico interior blanco, bolsa 30kg, fácil lijado", 40, 28.60f, pintureria},
            
            // Tornillería
            {"Tornillos Madera 4x40 x100", "Tornillos para madera 4x40mm cabeza pozidriv, zincados, pack x100", 150, 12.90f, tornilleria},
            {"Tuercas Hexagonales M8", "Tuercas hexagonales M8 zincadas, rosca métrica fina, pack x50", 200, 8.75f, tornilleria},
            {"Arandelas Planas M10", "Arandelas planas M10 acero zincado, norma DIN 125, pack x100", 180, 6.50f, tornilleria},
            {"Bulones Hexagonales M12", "Bulones hexagonales M12x80mm zincados, rosca completa, pack x25", 90, 22.30f, tornilleria},
            
            // Cerrajería
            {"Cerradura Pomo Doble", "Cerradura pomo doble cilindro 60mm, latón satinado, 3 llaves", 30, 89.90f, cerrajeria},
            {"Candado Seguridad 50mm", "Candado alta seguridad 50mm, arco templado, 3 llaves", 60, 35.75f, cerrajeria},
            {"Bisagra Puerta 3\"", "Bisagra puerta 3 pulgadas acero inoxidable, perno removible", 120, 18.50f, cerrajeria},
            {"Picaporte Colonial", "Picaporte colonial latón antiguo, para puertas interiores", 45, 42.80f, cerrajeria},
            
            // Soldadura
            {"Electrodo 6013 2.5mm", "Electrodos 6013 diámetro 2.5mm, soldadura universal, pack x5kg", 35, 18.90f, soldadura},
            {"Máscara Fotosensible", "Máscara soldadura fotosensible automática, filtro DIN 9-13", 20, 125.00f, soldadura},
            {"Soplete Oxigas", "Soplete oxigas para corte, válvulas independientes, pico intercambiable", 15, 89.50f, soldadura},
            
            // Seguridad
            {"Casco Seguridad Blanco", "Casco seguridad industrial blanco, ajuste por cremallera, norma IRAM", 80, 25.90f, seguridad},
            {"Guantes Cuero Descarne", "Guantes cuero descarne reforzados, puño largo, talle L", 100, 18.50f, seguridad},
            {"Anteojos Protección", "Anteojos protección cristal incoloro, patillas ajustables, anti-empañe", 90, 12.75f, seguridad},
            {"Botiquín Primeros Auxilios", "Botiquín primeros auxilios completo, gabinete metálico con llave", 25, 45.80f, seguridad}
        };

        int productosCreados = 0;
        for (Object[] data : productosData) {
            if (data[4] != null) { // Solo crear si la categoría existe
                ProductoModel producto = new ProductoModel();
                producto.setNombre((String) data[0]);
                producto.setDescripcion((String) data[1]);
                producto.setStock((Integer) data[2]);
                producto.setPrecio_unitario((Float) data[3]);
                producto.setCategoria((ProductoCategoriaModel) data[4]);
                producto.setImgUrl(null); // Sin imágenes por ahora
                
                productoRepository.save(producto);
                productosCreados++;
            }
        }

        System.out.println("✅ Productos creados: " + productosCreados);
    }
}