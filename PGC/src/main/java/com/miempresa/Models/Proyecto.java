package com.miempresa.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Proyecto {

    private String id;
    private String nombre;
    private String descripcion;
    private double presupuesto;
    private String fechaLimite;
    private String estado; // "Disponible", "En Proceso", "Finalizado"
    private String nombreContratistaAsignado;
    private List<Postulacion> postulaciones;


    public Proyecto(String nombre, String descripcion, double presupuesto, String fechaLimite) {
        this.id = UUID.randomUUID().toString(); // ID único automático
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.presupuesto = presupuesto;
        this.fechaLimite = fechaLimite;
        this.estado = "Disponible";
        this.nombreContratistaAsignado = null;
        this.postulaciones = new ArrayList<>();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public String getEstado() {
        return estado;
    }

    public String getNombreContratistaAsignado() {
        return nombreContratistaAsignado;
    }


    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNombreContratistaAsignado(String nombreContratistaAsignado) {
        this.nombreContratistaAsignado = nombreContratistaAsignado;
    }

    // Métodos adicionales
    public void agregarPostulacion(Postulacion postulacion) {
        this.postulaciones.add(postulacion);
    }

    public void seleccionarContratista(String nombreContratista) {
        this.nombreContratistaAsignado = nombreContratista;
        this.estado = "En Proceso";
    }

    public void finalizarProyecto() {
        this.estado = "Finalizado";
    }



    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setContratistaAsignado(String nombreContratista) {
    }
}
