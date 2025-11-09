package com.example.ferreteria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.EspecialistaModel;
import com.example.ferreteria.model.OficioModel;
import com.example.ferreteria.repository.EspecialistaRepository;

import jakarta.persistence.criteria.Predicate;

@Repository
public class EspecialistaDao {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    // devuelve todos los registros
    public List<EspecialistaModel> obtenerTodo() {
        return especialistaRepository.findAll();
    }
 
    /** 
    @param categoria
     * @param oficio
     * @param diponibilidad
     * @param puntuacion
     * @param nombre
     * @return
    */

    public List<EspecialistaModel> buscarConFiltros(OficioModel oficio, boolean diponibilidad, int puntuacion, String nombre) {

        Specification<EspecialistaModel> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (oficio != null && !oficio.getNombre().isEmpty()) { // Corregido: 
                predicates.add(criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("oficio").get("nombre")), 
                oficio.getNombre().toLowerCase() // 
                ));
            }

            if (puntuacion > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("puntuacion"), puntuacion));
            }

            // Filtro por precio máximo: WHERE precio_unitario <= max
            if (puntuacion > 0) { 
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("puntuacion"), puntuacion));
            }

            if (diponibilidad) { // Si diponibilidad es true
                predicates.add(criteriaBuilder.greaterThan(root.get("diponibilidad"), 0));
            } else { // Si diponibilidad es false
                predicates.add(criteriaBuilder.equal(root.get("diponibilidad"), 0));
            }

            // Filtro por nombre con búsqueda parcial: WHERE LOWER(nombre) LIKE '%nombre%'
            if (nombre != null && !nombre.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                criteriaBuilder.lower(root.get("nombre")), // Aplicamos lower() a la columna
                "%" + nombre.toLowerCase() + "%" // Aplicamos lower() al valor de búsqueda
                ));
            }

            // Combina todos los predicados con AND: WHERE condicion1 AND condicion2 AND ...
            // Si no hay predicados, devuelve todos los registros
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        // Ejecuta la query en la base de datos con los filtros aplicados
        return especialistaRepository.findAll(spec);
    }


    // busca un registro por su ID
    public Optional<EspecialistaModel> obtenerPorId(Long id) {
        return especialistaRepository.findById(id);
    }

    // guarda un nuevo registro
    public EspecialistaModel crear(EspecialistaModel especialista) {
        return especialistaRepository.save(especialista);
    }

    // borra un registro por ID
    public boolean eliminar(Long id) {
        if (especialistaRepository.existsById(id)) {
            especialistaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // modifica un registro existente
    public EspecialistaModel actualizar(Long id, EspecialistaModel especialistaActualizado) {
        return especialistaRepository.findById(id).map(especialista -> {
            especialista.setNombre(especialistaActualizado.getNombre());
            especialista.setApellido(especialistaActualizado.getApellido());
            especialista.setOficio(especialistaActualizado.getOficio());
            especialista.setDisponibilidad(especialistaActualizado.isDisponibilidad());
            especialista.setPuntuacion(especialistaActualizado.getPuntuacion());
            especialista.setPerfilImgUrl(especialistaActualizado.getPerfilImgUrl());
            return especialistaRepository.save(especialista);
        }).orElse(null);
    }
}