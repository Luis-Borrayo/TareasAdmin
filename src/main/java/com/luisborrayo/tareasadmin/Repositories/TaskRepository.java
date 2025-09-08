package com.luisborrayo.tareasadmin.Repositories;
import com.luisborrayo.tareasadmin.models.Tareas;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TaskRepository {

    private final Map<Long, Tareas> data = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(0);

    @PostConstruct
    void seed() {
        save(new Tareas(null, "Estudiar CDI", "Repasar anotaciones @Inject, @Named, @SessionScoped", "2025-09-07"));
        save(new Tareas(null, "Ejercicio", "Salir a correr 30 minutos", "2025-09-07"));
        save(new Tareas(null, "Proyecto PrimeFaces", "Completar CRUD de tareas con tabla interactiva", "2025-09-07"));
    }

    public List<Tareas> findAll() {
        ArrayList<Tareas> list = new ArrayList<>(data.values());
        list.sort(Comparator.comparing(Tareas::getId));
        return list;
    }

    public Optional<Tareas> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    public Tareas save(Tareas t) {
        if (t.getId() == null) t.setId(seq.incrementAndGet());
        data.put(t.getId(), t);
        return t;
    }

    public void deleteById(Long id) {
        data.remove(id);
    }
}