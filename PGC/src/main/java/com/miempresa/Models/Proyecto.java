package com.miempresa.Models;

import java.util.UUID;

public class Proyecto {

    private String id;
    private String nombre;
    private String descripcion;
    private double presupuesto;
    private String fechaLimite;
    private String estado;
    private String nombreContratistaAsignado;

    public Proyecto(String nombre, String descripcion, double presupuesto, String fechaLimite) {
        this.id = UUID.randomUUID().toString(); // Generación automática de ID único
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.fechaLimite = fechaLimite;
        this.estado = "Disponible";
    }

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPresupuesto() { return presupuesto; }
    public String getFechaLimite() { return fechaLimite; }
    public String getEstado() { return estado; }
    public String getNombreContratistaAsignado() { return nombreContratistaAsignado; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }
    public void setFechaLimite(String fechaLimite) { this.fechaLimite = fechaLimite; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setNombreContratistaAsignado(String nombreContratistaAsignado) {
        this.nombreContratistaAsignado = nombreContratistaAsignado;
    }


    // Métodos de gestión del estado
    public void seleccionarcontratista(String nombreContratista) {
        this.nombreContratistaAsignado = nombreContratista;
        this.estado = "En Proceso";
    }

    public void finalizarProyecto() {
        this.estado = "Finalizado";
    }
}
