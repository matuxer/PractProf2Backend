package com.example.ferreteria.repository;

import org.springframework.stereotype.Repository;

import com.example.ferreteria.model.ItemModel;

@Repository
public class ItemRepository extends JpaRepository<ItemModel, Integer> {

}
