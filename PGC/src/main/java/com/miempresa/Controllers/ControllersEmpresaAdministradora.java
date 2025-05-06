package com.miempresa.Controllers;

import com.miempresa.Models.Usuario;
import com.miempresa.Vista.VistaEmpresaAdministradora;
import com.miempresa.Vista.VistaLogin;

import javax.swing.*;
import java.util.Map;

public class ControllersEmpresaAdministradora {
    private Map<String, Usuario> mapaUsuarios;
    private VistaEmpresaAdministradora vista;
    private VistaLogin vistaLogin;

    public ControllersEmpresaAdministradora(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        vista = new VistaEmpresaAdministradora(vistaLogin, mapaUsuarios);
        inicializarEventos();
    }

    private void inicializarEventos() {
        // Acción para Ver Contratistas
        vista.getBtnVerContratistas().addActionListener(e -> {
            JOptionPane.showMessageDialog(vista.getFrame(), "Función para ver contratistas aún no implementada.");
        });

        // Acción para Registrar Trabajador
        vista.getBtnRegistrarTrabajador().addActionListener(e -> {
            JOptionPane.showMessageDialog(vista.getFrame(), "Función para registrar trabajadores aún no implementada.");
        });

        // Acción para Administrar Usuarios
        vista.getBtnAdministrarUsuarios().addActionListener(e -> {
            JOptionPane.showMessageDialog(vista.getFrame(), "Función para administrar usuarios aún no implementada.");
        });

        // Acción para Cerrar Sesión
        vista.getBtnCerrarSesion().addActionListener(e -> {
            vista.getFrame().dispose(); // Cierra la vista actual
            new VistaLogin(mapaUsuarios);
        });
    }
}
