package com.luisborrayo.tareasadmin.models;

import java.util.Objects;

public class Tareas implements java.io.Serializable {
    private Long id;
    private String nombre;
    private String descripcion;
    private String fechaCreacion;

    public Tareas(){
    }
    public Tareas(Long id, String nombre, String descripcion, String fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Tareas)) return false;
        return Objects.equals(id, ((Tareas)obj).id);
    }
    @Override
    public int hashCode() {return Objects.hashCode(id);}
}


