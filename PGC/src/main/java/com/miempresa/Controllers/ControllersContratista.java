package com.miempresa.Controllers;

import com.miempresa.Models.Usuario;
import com.miempresa.Vista.VistaContratista;
import com.miempresa.Vista.VistaLogin;

import javax.swing.*;
import java.util.Map;

public class ControllersContratista {
    private Map<String, Usuario> mapaUsuarios;
    private VistaContratista vista;
    private VistaLogin vistaLogin;

    public ControllersContratista(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        vista = new VistaContratista(vistaLogin,mapaUsuarios);
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        inicializarAcciones();
    }

    private void inicializarAcciones() {
        vista.getBtnVerProyectos().addActionListener(e -> verProyectos());
        vista.getBtnVerContratos().addActionListener(e -> verContratos());
        vista.getBtnActualizarEstadoContrato().addActionListener(e -> actualizarEstadoContrato());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void verProyectos() {
        // TODO: Lógica para mostrar los proyectos disponibles para el contratista
        JOptionPane.showMessageDialog(vista.getFrame(), "Ver proyectos disponibles");
    }

    private void verContratos() {
        // TODO: Lógica para ver los contratos existentes para este contratista
        JOptionPane.showMessageDialog(vista.getFrame(), "Ver contratos existentes");
    }

    private void actualizarEstadoContrato() {
        // TODO: Lógica para actualizar el estado de un contrato
        JOptionPane.showMessageDialog(vista.getFrame(), "Actualizar estado contrato");
    }

    private void cerrarSesion() {
        // Cerrar la vista actual y regresar a la vista de login
        vista.getFrame().dispose();
        new VistaLogin(mapaUsuarios);
    }
}
