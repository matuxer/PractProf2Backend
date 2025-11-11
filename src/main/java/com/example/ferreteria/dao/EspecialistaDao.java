package com.example.ferreteria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.EspecialistaModel;
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
     * Busca especialistas aplicando filtros dinámicos
     * @param nombreOficio Nombre del oficio del especialista
     * @param disponibilidad Si es true, trae especialistas disponibles. Si es false, no disponibles
     * @param puntuacion Puntuación mínima del especialista (1-5)
     * @param nombre Búsqueda parcial por nombre, apellido o nombre completo - case insensitive
     *               Ejemplos: "car" encuentra "Carlos", "car gon" encuentra "Carlos González"
     * @return Lista de especialistas que cumplen todos los filtros aplicados
     */
    public List<EspecialistaModel> buscarConFiltros(String nombreOficio, Boolean disponibilidad, Integer puntuacion, String nombre) {

        Specification<EspecialistaModel> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por oficio
            if (nombreOficio != null && !nombreOficio.isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("oficio").get("nombre")), 
                    nombreOficio.toLowerCase()
                ));
            }

            // Filtro por puntuación mínima
            if (puntuacion != null && puntuacion > 0) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("puntuacion"), puntuacion));
            }

            // Filtro por disponibilidad
            if (disponibilidad != null) {
                predicates.add(criteriaBuilder.equal(root.get("disponibilidad"), disponibilidad));
            }

            // Filtro por nombre, apellido o nombre completo
            // Busca en: nombre solo, apellido solo, o "nombre apellido" concatenado
            // Ejemplos: "car" → Carlos, Carmen, Ricardo; "gon" → González; "carlos gon" → Carlos González
            if (nombre != null && !nombre.isEmpty()) {
                String searchTerm = "%" + nombre.toLowerCase() + "%";
                
                // Concatena nombre + espacio + apellido para buscar nombre completo
                // Ejemplo: LOWER(CONCAT(nombre, ' ', apellido)) LIKE '%carlos gon%'
                Predicate nombreCompleto = criteriaBuilder.like(
                    criteriaBuilder.lower(
                        criteriaBuilder.concat(
                            criteriaBuilder.concat(root.get("nombre"), " "),
                            root.get("apellido")
                        )
                    ),
                    searchTerm
                );
                
                // Busca solo en nombre: LOWER(nombre) LIKE '%car%'
                Predicate soloNombre = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nombre")),
                    searchTerm
                );
                
                // Busca solo en apellido: LOWER(apellido) LIKE '%gon%'
                Predicate soloApellido = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("apellido")),
                    searchTerm
                );
                
                // Combina las tres condiciones con OR: (nombre LIKE '%x%' OR apellido LIKE '%x%' OR nombre_completo LIKE '%x%')
                predicates.add(criteriaBuilder.or(soloNombre, soloApellido, nombreCompleto));
            }

            // Combina todos los predicados con AND
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