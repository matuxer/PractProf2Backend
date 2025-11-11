package com.example.ferreteria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ItemModel;

@Repository
public interface  ItemRepository extends JpaRepository<ItemModel, Long> {

}
