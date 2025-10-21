package dao;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ItemDao {
    
    List<Item> obtenerTodo();

    List<Item> obtenerPorId(int id);

    void crear();

    void eliminar(int id);

    void actualizar(int id);

}
