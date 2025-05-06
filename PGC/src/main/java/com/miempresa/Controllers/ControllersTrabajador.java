package com.miempresa.Controllers;

import com.miempresa.Models.Usuario;
import com.miempresa.Vista.VistaTrabajador;
import com.miempresa.Vista.VistaLogin;

import javax.swing.*;
import java.util.Map;

public class ControllersTrabajador {

    private VistaTrabajador vista;
    private VistaLogin vistaLogin;
    private Map<String, Usuario> mapaUsuarios;

    public ControllersTrabajador(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        vista = new VistaTrabajador(vistaLogin,mapaUsuarios);
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        inicializarAcciones();
    }

    private void inicializarAcciones() {
        vista.getBtnVerProyectos().addActionListener(e -> verProyectos());
        vista.getBtnPostularProyecto().addActionListener(e -> postularProyecto());
        vista.getBtnVerContratos().addActionListener(e -> verContratos());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void verProyectos() {
        // TODO: Lógica para mostrar los proyectos disponibles para el trabajador
        JOptionPane.showMessageDialog(vista.getFrame(), "Ver proyectos disponibles");
    }

    private void postularProyecto() {
        // TODO: Lógica para permitir que el trabajador se postule a un proyecto
        JOptionPane.showMessageDialog(vista.getFrame(), "Postularse a un proyecto");
    }

    private void verContratos() {
        // TODO: Lógica para ver los contratos asociados al trabajador
        JOptionPane.showMessageDialog(vista.getFrame(), "Ver contratos asociados");
    }

    private void cerrarSesion() {
        vista.getFrame().dispose();
        new VistaLogin(mapaUsuarios);
    }
}
