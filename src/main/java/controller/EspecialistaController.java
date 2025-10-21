package controller;

import model.EspecialistaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import repository.EspecialistaRepository;

@RestController
@RequestMapping("/especialistas")
public class EspecialistaController {

    @Autowired
    private EspecialistaRepository especialistaRepository;

    // GET para obtener todos los registros
    @GetMapping
    public List<EspecialistaModel> getAll() {
        return especialistaRepository.findAll();
    }

    // GET para btener un registro por ID
    @GetMapping("/{id}")
    public Optional<EspecialistaModel> getById(@PathVariable int id) {
        return especialistaRepository.findById(id);
    }

    // POST para crear un nuevo registro
    @PostMapping
    public EspecialistaModel create(@RequestBody EspecialistaModel especialista) {
        return especialistaRepository.save(especialista);
    }

    // PUT para ctualizar un registro existente
    @PutMapping("/{id}")
    public EspecialistaModel update(@PathVariable int id, @RequestBody EspecialistaModel especialistaActualizado) {
        return especialistaRepository.findById(id).map(especialista -> {
            especialista.setNombre(especialistaActualizado.getNombre());
            especialista.setApellido(especialistaActualizado.getApellido());
            especialista.setOficio(especialistaActualizado.getOficio());
            especialista.setDisponibilidad(especialistaActualizado.isDisponibilidad());
            especialista.setPuntuacion(especialistaActualizado.getPuntuacion());
            return especialistaRepository.save(especialista);
        }).orElse(null);
    }

    // DELETE para eliminar un registro por ID
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if (especialistaRepository.existsById(id)) {
            especialistaRepository.deleteById(id);
            return "Especialista con ID " + id + " eliminado correctamente.";
        } else {
            return "Especialista con ID " + id + " no encontrado.";
        }
    }
}
