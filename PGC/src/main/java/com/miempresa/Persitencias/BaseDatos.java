package com.miempresa.Persitencias;

import com.miempresa.Models.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDatos {
    
    // Mapa general de todos los usuarios
    public static Map<String, Usuario> mapaUsuarios = new HashMap<>();
    
    // Listas específicas por tipo de usuario
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
}
