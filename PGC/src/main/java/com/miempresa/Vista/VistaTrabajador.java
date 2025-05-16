package com.miempresa.Vista;

import com.miempresa.Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VistaTrabajador {

    private JFrame frame;
    private JButton btnVerProyectos, btnPostularProyecto, btnVerContratos, btnCerrarSesion;
    private Map<String, Usuario> mapaUsuarios;
    private VistaLogin vistaLogin;

    public VistaTrabajador(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        frame = new JFrame("Panel Trabajador");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel para contener los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Crear botones
        btnVerProyectos = new JButton("Ver Proyectos");
        btnPostularProyecto = new JButton("Postular Proyecto");
        btnVerContratos = new JButton("Ver Contratos");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Opcional: Personalización visual de botones
        JButton[] botones = {btnVerProyectos, btnPostularProyecto, btnVerContratos, btnCerrarSesion};
        for (JButton btn : botones) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(100, 150, 220));
            btn.setForeground(Color.WHITE);
            panel.add(btn);
        }

        // Agregar el panel al frame
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

    public JButton getBtnVerContratos() {
        return btnVerContratos;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
