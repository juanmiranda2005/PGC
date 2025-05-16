package com.miempresa.Models;

public class Postulacion {
    private static int contador = 1;
    private int id;
    private Usuario contratista; // Usuario con tipo CONTRATISTA
    private Proyecto proyecto;
    private String estado; // "Pendiente", "Aceptada", "Rechazada"
    private String mensaje;

    public Postulacion(Usuario contratista, Proyecto proyecto, String mensaje) {
        this.id = contador++;
        this.contratista = contratista;
        this.proyecto = proyecto;
        this.estado = "Pendiente";
        this.mensaje = mensaje;
    }

    public int getId() {
        return id;
    }

    public Usuario getContratista() {
        return contratista;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getNombreContratista() {
        return contratista.getNombre(); // Porque Usuario tiene getNombre()
    }

    @Override
    public String toString() {
        return "Postulación de " + contratista.getNombre() +
                " al proyecto \"" + proyecto.getNombre() +
                "\" - Estado: " + estado;
    }
}
