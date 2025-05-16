package com.miempresa.Controllers;

import com.miempresa.Models.Usuario;
import com.miempresa.Vista.VistaClienteNatural;
import com.miempresa.Vista.VistaEmpresaAdministradora;
import com.miempresa.Vista.VistaLogin;

import javax.swing.*;
import java.util.Map;

public class ControllersClienteNatural {

    private VistaClienteNatural vista;
    private VistaLogin vistaLogin;
    private Map<String, Usuario> mapaUsuarios;

    public ControllersClienteNatural(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        vista = new VistaClienteNatural(vistaLogin, mapaUsuarios);
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        inicializarAcciones();
    }

    private void inicializarAcciones() {
        vista.getBtnVerProyectos().addActionListener(e -> verProyectos());
        vista.getBtnPostularProyecto().addActionListener(e -> postularProyecto());
        vista.getBtnVerContratistas().addActionListener(e -> verContratistas());
        vista.getBtnAsignarContratista().addActionListener(e -> asignarContratista());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void verProyectos() {
        // TODO: Lógica para ver los proyectos disponibles
        JOptionPane.showMessageDialog(vista.getFrame(), "Ver proyectos disponibles");
    }

    private void postularProyecto() {
        // TODO: Lógica para postularse a un proyecto
        JOptionPane.showMessageDialog(vista.getFrame(), "Postular proyecto");
    }

    private void verContratistas() {
        // TODO: Lógica para ver los contratistas disponibles
        JOptionPane.showMessageDialog(vista.getFrame(), "Ver contratistas disponibles");
    }

    private void asignarContratista() {
        // TODO: Lógica para asignar un contratista a un proyecto
        JOptionPane.showMessageDialog(vista.getFrame(), "Asignar contratista");
    }

    private void cerrarSesion() {
        vista.getFrame().dispose();
        new VistaLogin(mapaUsuarios);
    }
}
