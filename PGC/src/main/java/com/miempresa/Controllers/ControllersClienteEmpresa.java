package com.miempresa.Controllers;

import com.miempresa.Models.Usuario;
import com.miempresa.Vista.VistaClienteEmpresa;
import com.miempresa.Vista.VistaLogin;

import javax.swing.*;
import java.util.Map;

public class ControllersClienteEmpresa {
    private VistaClienteEmpresa vista;
    private VistaLogin vistaLogin;
    private Map<String, Usuario> mapaUsuarios;

    public ControllersClienteEmpresa(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        this.vista = new VistaClienteEmpresa(vistaLogin, mapaUsuarios);
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        inicializarAcciones();
    }

    private void inicializarAcciones() {
        vista.getBtnPublicarProyecto().addActionListener(e -> mostrarFormularioPublicacion());
        vista.getBtnVerPostulaciones().addActionListener(e -> mostrarPostulaciones());
        vista.getBtnSeleccionarContratista().addActionListener(e -> seleccionarContratista());
        vista.getBtnGenerarContrato().addActionListener(e -> generarContrato());
        vista.getBtnCerrarSesion().addActionListener(e -> cerrarSesion());
    }

    private void mostrarFormularioPublicacion() {
        // TODO: Lógica para mostrar formulario y guardar proyecto
        JOptionPane.showMessageDialog(vista.getFrame(), "Formulario para publicar proyecto");
    }

    private void mostrarPostulaciones() {
        // TODO: Mostrar postulaciones recibidas para proyectos de este cliente
        JOptionPane.showMessageDialog(vista.getFrame(), "Mostrar postulaciones");
    }

    private void seleccionarContratista() {
        // TODO: Permitir elegir un contratista para un proyecto
        JOptionPane.showMessageDialog(vista.getFrame(), "Seleccionar contratista");
    }

    private void generarContrato() {
        // TODO: Generar contrato en base al contratista y proyecto elegido
        JOptionPane.showMessageDialog(vista.getFrame(), "Generar contrato");
    }

    private void cerrarSesion() {
        vista.getFrame().dispose();
        new VistaLogin(mapaUsuarios);
    }
}
