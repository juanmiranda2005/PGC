package com.miempresa.Controllers;

import com.miempresa.Models.Proyecto;
import com.miempresa.Models.Usuario;
import com.miempresa.Persitencias.BaseDatos;
import com.miempresa.Vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import static com.miempresa.Persitencias.BaseDatos.mapaProyectos;

public class ControllersClienteEmpresa {
    private VistaClienteEmpresa vista;
    private VistaLogin vistaLogin;
    private Map<String, Usuario> mapaUsuarios;


    public ControllersClienteEmpresa(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        this.vista = new VistaClienteEmpresa(vistaLogin, mapaUsuarios);
    }


    private void cerrarSesion() {
        vista.getFrame().dispose();
        new VistaLogin(mapaUsuarios);
    }

}