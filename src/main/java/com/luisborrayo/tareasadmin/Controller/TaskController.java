package com.luisborrayo.tareasadmin.Controller;


import com.luisborrayo.tareasadmin.models.Tareas;
import com.luisborrayo.tareasadmin.service.TaskService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named("taskController")
@SessionScoped
public class TaskController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;

    @Inject
    private TaskService service;

    public List<Tareas> getTareas() {
        return service.listar();
    }

    public void agregar() {
        if (nombre == null || nombre.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Validación", "El título es obligatorio"));
            return;
        }
        String fecha = LocalDate.now().toString(); // formatea YYYY-MM-DD
        Tareas t = service.crear(nombre.trim(), descripcion == null ? "" : descripcion.trim(), fecha);
        nombre = "";
        descripcion = "";
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea agregada", "ID " + t.getId()));
    }

    public void eliminar(Tareas tarea) {
        if (tarea == null || tarea.getId() == null) return;
        boolean ok = service.eliminar(tarea.getId());
        if (ok) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Tarea eliminada", tarea.getNombre()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void completar(Tareas tarea) {
        if (tarea == null || tarea.getId() == null) return;
        boolean ok = service.completar(tarea.getId());
        if (ok) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Tarea completada", tarea.getNombre()));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo marcar como completada"));
        }
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}