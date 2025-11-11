package com.example.ferreteria.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ferreteria.dao.ClienteDao;
import com.example.ferreteria.dao.CompraDao;
import com.example.ferreteria.dao.ItemDao;
import com.example.ferreteria.dao.ProductoDao;
import com.example.ferreteria.dto.FinalizarCompraRequest;
import com.example.ferreteria.dto.ItemRequest;
import com.example.ferreteria.model.ClienteModel;
import com.example.ferreteria.model.CompraModel;
import com.example.ferreteria.model.ItemModel;
import com.example.ferreteria.model.ProductoModel;

@RestController
@RequestMapping("/compra")
public class CompraController {
      
//-GetAll (Obtener todos los registros) GET
//-GetById (Obtener 1 solo registro por Id) GET
//-Create (Crear un registro) POST
//-Update (Actualizar un registro) PUT
//-Delete (Borrar un registro) DELETE

    @Autowired
    private CompraDao compraDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private ItemDao itemDao;

    @GetMapping
    public List<CompraModel> getAll() {
        return compraDao.obtenerTodo();
    }

    //Se deja como Optional por motivo de error
    //Continuar analizando
    //Consultar en daily
    @GetMapping("/{id}")
    public Optional<CompraModel> getById(@PathVariable Long id) {
        return compraDao.obtenerPorId(id);
    }

    @PostMapping
    public CompraModel create(@RequestBody CompraModel compra) {
        return compraDao.crear(compra);
    }

    @PutMapping("/{id}")
    public CompraModel update(@PathVariable Long id, @RequestBody CompraModel compra) {
        compra.setId(id);
        return compraDao.actualizar(id, compra);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (compraDao.eliminar(id)) {
            return "Item con ID " + id + " eliminado correctamente.";
        } else {
            return "Item con ID " + id + " no encontrado.";
        }
    }

    @PostMapping("/finalizar")
    public ResponseEntity<?> finalizarCompra(@RequestBody FinalizarCompraRequest request) {
        try {
            // 1. Validar que el cliente existe
            ClienteModel cliente = clienteDao.obtenerPorId(request.getClienteId());
            if (cliente == null) {
                return ResponseEntity.badRequest().body("Cliente no encontrado");
            }

            // 2. Crear la compra
            CompraModel compra = new CompraModel();
            compra.setCliente(cliente);
            compra.setDescuento(request.getDescuento());
            compra.setTotal(0); // Se calculará después
            compra.setFechaCompra(LocalDate.now()); // Fecha actual
            
            // Guardar la compra primero para obtener el ID
            compra = compraDao.crear(compra);

            // 3. Procesar cada item del carrito
            float subtotal = 0;
            
            for (ItemRequest itemReq : request.getItems()) {
                // Validar que el producto existe
                ProductoModel producto = productoDao.obtenerPorId(itemReq.getProductoId());
                if (producto == null) {
                    return ResponseEntity.badRequest().body("Producto con ID " + itemReq.getProductoId() + " no encontrado");
                }

                // Validar stock suficiente
                if (producto.getStock() < itemReq.getCantidad()) {
                    return ResponseEntity.badRequest().body("Stock insuficiente para el producto: " + producto.getNombre());
                }

                // Crear el item
                ItemModel item = new ItemModel();
                item.setProducto(producto);
                item.setCantidad(itemReq.getCantidad());
                item.setPrecio_total(itemReq.getCantidad() * itemReq.getPrecioUnitario());
                item.setCompra(compra);
                
                itemDao.crear(item);

                // Acumular subtotal
                subtotal += item.getPrecio_total();

                // Actualizar stock del producto
                producto.setStock(producto.getStock() - itemReq.getCantidad());
                productoDao.actualizar(producto.getId(), producto);
            }

            // 4. Actualizar el total de la compra
            compra.setTotal(subtotal - request.getDescuento());
            compra = compraDao.actualizar(compra.getId(), compra);

            // 5. Retornar la compra creada
            return ResponseEntity.ok(compra);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar la compra: " + e.getMessage());
        }
    }

}
