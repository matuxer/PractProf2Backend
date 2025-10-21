package controller;

import dao.EspecialistaDao;
import model.EspecialistaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialistas")

public class EspecialistaController {

    @Autowired
    private EspecialistaDao especialistaDao;

    // GET para obtener todos los registros
    @GetMapping
    public List<EspecialistaModel> getAll() {
        return especialistaDao.obtenerTodo();
    }

    // GET para obtener un registro por ID
    @GetMapping("/{id}")
    public EspecialistaModel getById(@PathVariable int id) {
        return especialistaDao.obtenerPorId(id).orElse(null);
    }

    // POST para crear un nuevo registro
    @PostMapping
    public EspecialistaModel create(@RequestBody EspecialistaModel especialista) {
        return especialistaDao.crear(especialista);
    }

    // PUT para actualizar un registro existente
    @PutMapping("/{id}")
    public EspecialistaModel update(@PathVariable int id, @RequestBody EspecialistaModel especialistaActualizado) {
        return especialistaDao.actualizar(id, especialistaActualizado);
    }

    // DELETE para eliminar un registro por ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if (especialistaDao.eliminar(id)) {
            return "Especialista con ID " + id + " eliminado correctamente.";
        } else {
            return "Especialista con ID " + id + " no encontrado.";
        }
    }
}
