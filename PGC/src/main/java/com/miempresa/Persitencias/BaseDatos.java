package com.miempresa.Persitencias;

import com.miempresa.Models.Proyecto;
import com.miempresa.Models.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDatos {

    public static Map<String, Proyecto> mapaProyectos = new HashMap<>();
    public static List<Proyecto> proyectosDisponibles = new ArrayList<>();
    public static List<Proyecto> proyectosEnProceso = new ArrayList<>();
    public static List<Proyecto> proyectosFinalizados = new ArrayList<>();

    public static Map<String, Usuario> mapaUsuarios = new HashMap<>();

    public static List<Usuario> listaClientesNaturales = new ArrayList<>();
    public static List<Usuario> listaContratistas = new ArrayList<>();
    public static List<Usuario> listaClientesEmpresa = new ArrayList<>();
    public static List<Usuario> listaTrabajadores = new ArrayList<>();
    public static List<Usuario> listaEmpresasAdministrador = new ArrayList<>();
    
    public static void agregarUsuario(Usuario usuario) {
        mapaUsuarios.put(usuario.getUsername(), usuario);
        
        switch (usuario.getTipoUsuario()) {
            case CLIENTE_NATURAL:
                listaClientesNaturales.add(usuario);
                break;
            case CONTRATISTA:
                listaContratistas.add(usuario);
                break;
            case CLIENTE_EMPRESA:
                listaClientesEmpresa.add(usuario);
                break;
            case TRABAJADOR:
                listaTrabajadores.add(usuario);
                break;
            case EMPRESA_ADMINISTRADORA:
                listaEmpresasAdministrador.add(usuario);
                break;
        }
    }
    
    // Método para buscar un usuario por username
    public static Usuario buscarUsuario(String username) {
        return mapaUsuarios.get(username);
    }
    
    // Método para eliminar un usuario
    public static void eliminarUsuario(String username) {
        Usuario usuario = mapaUsuarios.remove(username);
        
        if (usuario != null) {
            switch (usuario.getTipoUsuario()) {
                case CLIENTE_NATURAL:
                    listaClientesNaturales.remove(usuario);
                    break;
                case CONTRATISTA:
                    listaContratistas.remove(usuario);
                    break;
                case CLIENTE_EMPRESA:
                    listaClientesEmpresa.remove(usuario);
                    break;
                case TRABAJADOR:
                    listaTrabajadores.remove(usuario);
                    break;
                case EMPRESA_ADMINISTRADORA:
                    listaEmpresasAdministrador.remove(usuario);
                    break;
            }
        }
    }

    // Método para agregar proyecto y clasificarlo
    public static void agregarProyecto(Proyecto proyecto) {
        mapaProyectos.put(proyecto.getId(), proyecto);

        switch (proyecto.getEstado()) {
            case "Disponible" -> proyectosDisponibles.add(proyecto);
            case "En Proceso" -> proyectosEnProceso.add(proyecto);
            case "Finalizado" -> proyectosFinalizados.add(proyecto);
        }
    }

    public static List<Proyecto> getProyectosDisponibles() {
        List<Proyecto> disponibles = new ArrayList<>();
        for (Proyecto proyecto : mapaProyectos.values()) {
            if (proyecto.getEstado().equalsIgnoreCase("Disponible")) {
                disponibles.add(proyecto);
            }
        }
        return disponibles;
    }

    public static List<Proyecto> getProyectosEnProceso() {
        List<Proyecto> enProceso = new ArrayList<>();
        for (Proyecto proyecto : mapaProyectos.values()) {
            // Cambié "En proceso" a "En Proceso"
            if (proyecto.getEstado().equalsIgnoreCase("En Proceso")) {
                enProceso.add(proyecto);
            }
        }
        return enProceso;
    }

    public static List<Proyecto> getProyectosFinalizados() {
        List<Proyecto> finalizados = new ArrayList<>();
        for (Proyecto proyecto : mapaProyectos.values()) {
            if (proyecto.getEstado().equalsIgnoreCase("Finalizado")) {
                finalizados.add(proyecto);
            }
        }
        return finalizados;
    }

    public static List<Proyecto> getTodosProyectos() {
        return new ArrayList<>(mapaProyectos.values());
    }


}
