package com.luisborrayo.tareasadmin.service;

import com.luisborrayo.tareasadmin.Repositories.TaskRepository;
import com.luisborrayo.tareasadmin.models.Tareas;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TaskService {

    @Inject
    private TaskRepository repo;

    public Tareas crear(String nombre, String descripcion, String fechaCreacion) {
        Tareas t = new Tareas(null, nombre, descripcion, fechaCreacion);
        return repo.save(t);
    }

    public List<Tareas> listar() {
        return repo.findAll();
    }

    public Optional<Tareas> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public boolean eliminar(Long id) {
        Optional<Tareas> op = repo.findById(id);
        if (op.isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean completar(Long id) {
        // Como tu modelo no tiene campo "completada", podemos simularlo eliminando o marcando en descripcion.
        // Mejor opción: extender modelo con boolean completada. Pero aquí mostraremos un ejemplo simple que
        // agrega la palabra "(COMPLETADA)" al nombre si no existe.
        Optional<Tareas> op = repo.findById(id);
        if (op.isPresent()) {
            Tareas t = op.get();
            if (t.getNombre() == null) t.setNombre("");
            if (!t.getNombre().contains("(COMPLETADA)")) {
                t.setNombre(t.getNombre() + " (COMPLETADA)");
                repo.save(t);
            }
            return true;
        }
        return false;
    }
}
