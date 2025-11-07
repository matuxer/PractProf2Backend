package com.example.ferreteria.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ferreteria.dao.ProductoCategoriaDao;
import com.example.ferreteria.model.ProductoCategoriaModel;
import com.example.ferreteria.model.ProductoModel;
import com.example.ferreteria.repository.ProductoRepository;

@Component
public class ProductoSeeder {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoCategoriaDao productoCategoriaDao;

    public void seed() {
        if (productoRepository.count() > 0) {
            System.out.println("🛠️ Productos ya existen, saltando seeder...");
            return;
        }

        System.out.println("🛠️ Creando productos...");

        // Obtener categorías
        ProductoCategoriaModel herramientasManuales = productoCategoriaDao.obtenerPorNombre("Herramientas Manuales");
        ProductoCategoriaModel herramientasElectricas = productoCategoriaDao.obtenerPorNombre("Herramientas Eléctricas");
        ProductoCategoriaModel materialesConstruccion = productoCategoriaDao.obtenerPorNombre("Materiales de Construcción");
        ProductoCategoriaModel plomeria = productoCategoriaDao.obtenerPorNombre("Plomería");
        ProductoCategoriaModel electricidad = productoCategoriaDao.obtenerPorNombre("Electricidad");
        ProductoCategoriaModel pintureria = productoCategoriaDao.obtenerPorNombre("Pinturería");
        ProductoCategoriaModel tornilleria = productoCategoriaDao.obtenerPorNombre("Tornillería");
        ProductoCategoriaModel cerrajeria = productoCategoriaDao.obtenerPorNombre("Cerrajería");
        ProductoCategoriaModel soldadura = productoCategoriaDao.obtenerPorNombre("Soldadura");
        ProductoCategoriaModel seguridad = productoCategoriaDao.obtenerPorNombre("Seguridad");

        // Lista de productos con detalles realistas e imágenes
        Object[][] productosData = {
            // Nombre, Descripción, Stock, Precio, Categoría, Imagen
            // Herramientas Manuales
            {"Martillo Carpintero 16oz", "Martillo de carpintero con mango de fibra de vidrio, cabeza forjada", 50, 15.99f, herramientasManuales, "Martillo_Carpintero_16oz.png"},
            {"Destornillador Phillips #2", "Destornillador Phillips punta #2, mango ergonómico antideslizante", 100, 8.50f, herramientasManuales, "Destornillador_Phillips_#2.png"},
            {"Alicate Universal 8\"", "Alicate universal 8 pulgadas, acero templado, mango aislado", 75, 22.75f, herramientasManuales, "Alicate_Universal_8_pulgadas.png"},
            {"Llave Inglesa 10\"", "Llave inglesa ajustable 10 pulgadas, acero al carbono", 60, 18.90f, herramientasManuales, "Llave_Inglesa_10_pulgadas.png"},
            {"Sierra de Costilla 14\"", "Sierra de costilla 14 pulgadas, dientes templados, mango de madera", 40, 28.50f, herramientasManuales, "Sierra_de_Costilla_14_pulgadas.png"},
            
            // Herramientas Eléctricas
            {"Taladro Eléctrico 600W", "Taladro eléctrico 600W con percutor, mandril 13mm, cable 2m", 25, 89.99f, herramientasElectricas, "Taladro_Eléctrico_600W.png"},
            {"Amoladora Angular 4.5\"", "Amoladora angular 4.5 pulgadas, 750W, 11000 RPM, mango auxiliar", 30, 67.50f, herramientasElectricas, "Amoladora_Angular_4.5_pulgadas.png"},
            {"Caladora 450W", "Caladora eléctrica 450W, corte pendular, base ajustable", 20, 75.90f, herramientasElectricas, "Caladora_450W.png"},
            {"Lijadora Orbital", "Lijadora orbital eléctrica 200W, base velcro, recolector de polvo", 18, 58.75f, herramientasElectricas, "Lijadora_Orbital.png"},
            
            // Materiales de Construcción
            {"Cemento Portland x 50kg", "Cemento Portland tipo I, bolsa 50kg, resistencia 42.5 MPa", 200, 12.50f, materialesConstruccion, "Cemento_Portland_x_50kg.png"},
            {"Ladrillo Común x 100", "Ladrillos comunes rojos, medidas estándar 6x12x25cm, pack x100", 150, 35.00f, materialesConstruccion, "Ladrillo_Común_x_100.png"},
            {"Arena Gruesa x m³", "Arena gruesa para construcción, lavada y clasificada, por metro cúbico", 80, 25.80f, materialesConstruccion, "Arena_Gruesa_x_m³.png"},
            {"Hierro 8mm x 12m", "Hierro construcción ADN420 diámetro 8mm, barra 12 metros", 120, 18.90f, materialesConstruccion, "Hierro_8mm_x_12m.png"},
            
            // Plomería
            {"Caño PVC 110mm x 3m", "Caño PVC desagüe 110mm x 3 metros, norma IRAM", 90, 22.40f, plomeria, "Caño_PVC_110mm_x_3m.png"},
            {"Grifo Monocomando Cocina", "Grifo monocomando para cocina, latón cromado, pico giratorio", 35, 125.00f, plomeria, "Grifo_Monocomando_Cocina.png"},
            {"Codo PVC 90° 50mm", "Codo PVC 90 grados diámetro 50mm para desagüe cloacal", 200, 3.75f, plomeria, "Codo_PVC_90°_50mm.png"},
            {"Sifón Lavatorio Cromado", "Sifón para lavatorio cromado, 1.1/4 pulgadas con tapón", 45, 28.90f, plomeria, "Sifón_Lavatorio_Cromado.png"},
            
            // Electricidad
            {"Cable TPR 2.5mm x 100m", "Cable TPR 2.5mm² x 100 metros, aislación termoplástica", 25, 89.50f, electricidad, "Cable_TPR_2.5mm_x_100m.png"},
            {"Interruptor Simple Blanco", "Interruptor simple 10A, blanco, norma IRAM 2071", 150, 8.75f, electricidad, "Interruptor_Simple_Blanco.png"},
            {"Toma Corriente 10A", "Toma corriente 10A con puesta a tierra, blanco, norma IRAM", 120, 12.30f, electricidad, "Toma_Corriente_10A.png"},
            {"Lámpara LED 12W", "Lámpara LED 12W luz fría 6500K, rosca E27, vida útil 25000hs", 80, 18.50f, electricidad, "Lámpara_LED_12W.png"},
            
            // Pinturería
            {"Pintura Látex Interior 4L", "Pintura látex acrílico interior blanco mate, 4 litros, lavable", 60, 32.90f, pintureria, "Pintura_Látex_Interior_4L.png"},
            {"Rodillo Lana 23cm", "Rodillo de lana natural 23cm, mango plástico ergonómico", 100, 15.75f, pintureria, "Rodillo_Lana_23cm.png"},
            {"Pincel Cerda 2\"", "Pincel cerda natural 2 pulgadas, virola inoxidable, mango madera", 80, 12.40f, pintureria, "Pincel_Cerda_2_pulgadas.png"},
            {"Enduido Plástico x 30kg", "Enduido plástico interior blanco, bolsa 30kg, fácil lijado", 40, 28.60f, pintureria, "Enduido_Plástico_x_30kg.png"},
            
            // Tornillería
            {"Tornillos Madera 4x40 x100", "Tornillos para madera 4x40mm cabeza pozidriv, zincados, pack x100", 150, 12.90f, tornilleria, "Tornillos_Madera_4x40_x100.png"},
            {"Tuercas Hexagonales M8", "Tuercas hexagonales M8 zincadas, rosca métrica fina, pack x50", 200, 8.75f, tornilleria, "Tuercas_Hexagonales_M8.png"},
            {"Arandelas Planas M10", "Arandelas planas M10 acero zincado, norma DIN 125, pack x100", 180, 6.50f, tornilleria, "Arandelas_Planas_M10.png"},
            {"Bulones Hexagonales M12", "Bulones hexagonales M12x80mm zincados, rosca completa, pack x25", 90, 22.30f, tornilleria, "Bulones_Hexagonales_M12.png"},
            
            // Cerrajería
            {"Cerradura Pomo Doble", "Cerradura pomo doble cilindro 60mm, latón satinado, 3 llaves", 30, 89.90f, cerrajeria, "Cerradura_Pomo_Doble.png"},
            {"Candado Seguridad 50mm", "Candado alta seguridad 50mm, arco templado, 3 llaves", 60, 35.75f, cerrajeria, "Candado_Seguridad_50mm.png"},
            {"Bisagra Puerta 3\"", "Bisagra puerta 3 pulgadas acero inoxidable, perno removible", 120, 18.50f, cerrajeria, "Bisagra_Puerta_3_pulgadas.png"},
            {"Picaporte Colonial", "Picaporte colonial latón antiguo, para puertas interiores", 45, 42.80f, cerrajeria, "Picaporte_Colonial.png"},
            
            // Soldadura
            {"Electrodo 6013 2.5mm", "Electrodos 6013 diámetro 2.5mm, soldadura universal, pack x5kg", 35, 18.90f, soldadura, "Electrodo_6013_2.5mm.png"},
            {"Máscara Fotosensible", "Máscara soldadura fotosensible automática, filtro DIN 9-13", 20, 125.00f, soldadura, "Máscara_Fotosensible.png"},
            {"Soplete Oxigas", "Soplete oxigas para corte, válvulas independientes, pico intercambiable", 15, 89.50f, soldadura, "Soplete_Oxigas.png"},
            
            // Seguridad
            {"Casco Seguridad Blanco", "Casco seguridad industrial blanco, ajuste por cremallera, norma IRAM", 80, 25.90f, seguridad, "Casco_Seguridad_Blanco.png"},
            {"Guantes Cuero Descarne", "Guantes cuero descarne reforzados, puño largo, talle L", 100, 18.50f, seguridad, "Guantes_Cuero_Descarne.png"},
            {"Anteojos Protección", "Anteojos protección cristal incoloro, patillas ajustables, anti-empañe", 90, 12.75f, seguridad, "Anteojos_Protección.png"},
            {"Botiquín Primeros Auxilios", "Botiquín primeros auxilios completo, gabinete metálico con llave", 25, 45.80f, seguridad, "Botiquín_Primeros_Auxilios.png"}
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
                producto.setImgUrl("/uploads/productos/" + (String) data[5]);
                
                productoRepository.save(producto);
                productosCreados++;
            }
        }

        System.out.println("✅ Productos creados: " + productosCreados);
    }
}