package com.miempresa.Vista;

import com.miempresa.Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VistaClienteNatural {

    private JFrame frame;
    private JButton btnVerProyectos;
    private JButton btnPostularProyecto;
    private JButton btnVerContratistas;
    private JButton btnAsignarContratista;  // Nuevo botón para asignar contratista
    private JButton btnCerrarSesion;
    private Map<String, Usuario> mapaUsuarios;
    private VistaLogin vistaLogin;

    public VistaClienteNatural(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        frame = new JFrame("Panel Cliente Natural");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 20, 20));  // Cambiar a 5 filas para agregar un nuevo botón
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        btnVerProyectos = new JButton("Ver Proyectos");
        btnPostularProyecto = new JButton("Postular Proyecto");
        btnVerContratistas = new JButton("Ver Contratistas");
        btnAsignarContratista = new JButton("Asignar Contratista");  // Botón para asignar contratista
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Personalización visual de los botones
        JButton[] botones = {btnVerProyectos, btnPostularProyecto, btnVerContratistas, btnAsignarContratista, btnCerrarSesion};
        for (JButton btn : botones) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(100, 150, 220));
            btn.setForeground(Color.WHITE);
            panel.add(btn);
        }

        frame.add(panel);
        frame.setVisible(true);

        // Acción por defecto de cerrar sesión
        btnCerrarSesion.addActionListener(e -> {
            frame.dispose();
            vistaLogin.getFrame().setVisible(true);
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnVerProyectos() {
        return btnVerProyectos;
    }

    public JButton getBtnPostularProyecto() {
        return btnPostularProyecto;
    }

    public JButton getBtnVerContratistas() {
        return btnVerContratistas;
    }

    public JButton getBtnAsignarContratista() {
        return btnAsignarContratista;  // Getter para el nuevo botón
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
