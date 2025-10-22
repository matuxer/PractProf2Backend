package com.example.ferreteria.dao;


@Repository
public Class ItemDao {
    
    @Autowired
    private ItemRepository itemRepository;

    List<Item> obtenerTodo();

    List<Item> obtenerPorId(int id);

    void crear();

    void eliminar(int id);

    void actualizar(int id);

}