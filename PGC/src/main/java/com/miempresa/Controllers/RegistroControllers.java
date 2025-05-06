package com.miempresa.Controllers;

import com.miempresa.Models.Usuario;
import com.miempresa.Persitencias.BaseDatos;


import javax.swing.*;

public class RegistroControllers {

    public RegistroControllers() {
        // Constructor vacío porque ya tenemos acceso a BaseDatos directamente
    }

    public boolean registrarUsuario(String username, String password, Usuario.TipoUsuario tipoUsuario,
                                    String nombre, String apellido, String correo, int identificacion) {

        if (username.isEmpty() || password.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(null, "Correo electrónico no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (BaseDatos.mapaUsuarios.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Usuario nuevo = new Usuario(username, password, tipoUsuario, nombre, apellido, correo, identificacion);
        BaseDatos.agregarUsuario(nuevo);  // usamos el método correcto
        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
}
