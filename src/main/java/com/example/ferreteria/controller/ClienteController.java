package com.example.ferreteria.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.example.ferreteria.dto.ClientePerfilResponse;
import com.example.ferreteria.dto.CompraResumenResponse;
import com.example.ferreteria.dto.FeedbackClienteResponse;
import com.example.ferreteria.model.ClienteModel;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteDao clienteDao;

    @GetMapping
    public List<ClienteModel> getAll(){
        return clienteDao.obtenerTodo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        ClienteModel cliente = clienteDao.obtenerPorId(id);
        
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        // Convertir compras a DTO
        List<CompraResumenResponse> compras = cliente.getCompras().stream()
            .map(compra -> {
                // Convertir items de la compra
                List<CompraResumenResponse.ItemResumen> items = compra.getItems().stream()
                    .map(item -> {
                        CompraResumenResponse.ProductoBasicInfo producto = null;
                        if (item.getProducto() != null) {
                            producto = new CompraResumenResponse.ProductoBasicInfo(
                                item.getProducto().getId(),
                                item.getProducto().getNombre(),
                                item.getProducto().getPrecio_unitario(),
                                item.getProducto().getImgUrl()
                            );
                        }
                        return new CompraResumenResponse.ItemResumen(
                            item.getId(),
                            item.getCantidad(),
                            item.getPrecio_total(),
                            producto
                        );
                    })
                    .collect(Collectors.toList());

                return new CompraResumenResponse(
                    compra.getId(),
                    compra.getFechaCompra(),
                    compra.getDescuento(),
                    compra.getTotal(),
                    items
                );
            })
            .collect(Collectors.toList());

        // Convertir feedbacks a DTO
        List<FeedbackClienteResponse> feedbacks = cliente.getFeedbacksEscritos().stream()
            .map(feedback -> {
                FeedbackClienteResponse.EspecialistaBasicInfo especialista = null;
                if (feedback.getEspecialista() != null) {
                    especialista = new FeedbackClienteResponse.EspecialistaBasicInfo(
                        feedback.getEspecialista().getId(),
                        feedback.getEspecialista().getNombre(),
                        feedback.getEspecialista().getApellido(),
                        feedback.getEspecialista().getOficio() != null ? feedback.getEspecialista().getOficio().getNombre() : null,
                        feedback.getEspecialista().getPerfilImgUrl()
                    );
                }
                return new FeedbackClienteResponse(
                    feedback.getId(),
                    feedback.getFecha(),
                    feedback.getClasificacion(),
                    feedback.getComentario(),
                    especialista
                );
            })
            .collect(Collectors.toList());

        // Crear respuesta del perfil
        ClientePerfilResponse response = new ClientePerfilResponse(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getCorreo(),
            cliente.getTelefono(),
            cliente.getPuntosRecompensa(),
            cliente.getDomicilio(),
            cliente.getLocalidad() != null ? cliente.getLocalidad().getNombre() : null,
            cliente.getProvincia() != null ? cliente.getProvincia().getNombre() : null,
            cliente.getPais() != null ? cliente.getPais().getNombre() : null,
            compras,
            feedbacks
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ClienteModel create(@RequestBody ClienteModel cliente){
        return clienteDao.crear(cliente);
    }

    @PutMapping("/{id}")
    public ClienteModel update(@PathVariable Long id, @RequestBody ClienteModel cliente){
        cliente.setId(id);
        return clienteDao.actualizar(id, cliente);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        if (clienteDao.eliminar(id)){
            return "El cliente con ID " + id + "eliminado correctamente";
        }else {
            return "El cliente con ID" + id + "no encontrado";
        }
    }
}
