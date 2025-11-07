package com.example.ferreteria.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ProductoModel;
import com.example.ferreteria.repository.ProductoRepository;

import jakarta.persistence.criteria.Predicate;

@Repository
public class ProductoDao {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoModel> obtenerTodo() {
        return productoRepository.findAll();
    }

    /**
     * Busca productos aplicando filtros dinámicos
     * Usa JPA Criteria API para construir una query SQL con WHERE en la base de datos
     * No filtra en memoria - la query se ejecuta directamente en la DB
     * 
     * @param categoria Nombre de la categoría (ej: "Electricidad") - case insensitive
     * @param min Precio mínimo (inclusivo)
     * @param max Precio máximo (inclusivo)
     * @param stock Si es true, trae productos CON stock (stock > 0). Si es false, trae productos SIN stock (stock = 0)
     * @param nombre Búsqueda parcial por nombre - case insensitive (ej: "tornillo" encuentra "Tornillos Madera")
     * @return Lista de productos que cumplen todos los filtros aplicados (se combinan con AND)
     */
    public List<ProductoModel> buscarConFiltros(String categoria, Float min, Float max, Boolean stock, String nombre) {
        // Specification: Permite construir queries dinámicas con JPA Criteria API
        // root: representa la tabla productos
        // query: representa la query SQL que se está construyendo
        // criteriaBuilder: constructor de predicados (condiciones WHERE)
        Specification<ProductoModel> spec = (root, query, criteriaBuilder) -> {
            // Lista de predicados (condiciones WHERE) que se irán agregando dinámicamente
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por categoría: WHERE LOWER(categoria.nombre) = 'electricidad'
            if (categoria != null && !categoria.isEmpty()) {
                predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("categoria").get("nombre")), // Navega a la relación categoria y obtiene su nombre
                    categoria.toLowerCase()
                ));
            }

            // Filtro por precio mínimo: WHERE precio_unitario >= min
            if (min != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("precio_unitario"), min));
            }

            // Filtro por precio máximo: WHERE precio_unitario <= max
            if (max != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("precio_unitario"), max));
            }

            // Filtro por stock: Si stock=true, trae productos CON stock (stock > 0)
            //                  Si stock=false, trae productos SIN stock (stock = 0)
            if (stock != null) {
                if (stock) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("stock"), 0));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get("stock"), 0));
                }
            }

            // Filtro por nombre con búsqueda parcial: WHERE LOWER(nombre) LIKE '%tornillo%'
            if (nombre != null && !nombre.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nombre")), 
                    "%" + nombre.toLowerCase() + "%" // % = wildcard SQL para búsqueda parcial
                ));
            }

            // Combina todos los predicados con AND: WHERE condicion1 AND condicion2 AND ...
            // Si no hay predicados, devuelve todos los registros
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        // Ejecuta la query en la base de datos con los filtros aplicados
        return productoRepository.findAll(spec);
    }

    public ProductoModel obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public ProductoModel crear(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductoModel actualizar(Long id, ProductoModel productoActualizado) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setStock(productoActualizado.getStock());
            producto.setPrecio_unitario(productoActualizado.getPrecio_unitario());
            producto.setImgUrl(productoActualizado.getImgUrl());
            return productoRepository.save(producto);
        }).orElse(null);
    }
}
