package com.miempresa.Vista;

import com.miempresa.Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class VistaEmpresaAdministradora {

    private JFrame frame;
    private JButton btnVerContratistas;
    private JButton btnRegistrarTrabajador;
    private JButton btnAdministrarUsuarios;
    private JButton btnCerrarSesion;
    private Map<String, Usuario> mapaUsuarios;
    private VistaLogin vistaLogin;


    public VistaEmpresaAdministradora(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        frame = new JFrame("Panel Empresa Administradora");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        btnVerContratistas = new JButton("Ver Contratistas");
        btnRegistrarTrabajador = new JButton("Registrar Trabajador");
        btnAdministrarUsuarios = new JButton("Administrar Usuarios");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        // Opcional: Personalización visual
        JButton[] botones = {btnVerContratistas, btnRegistrarTrabajador, btnAdministrarUsuarios, btnCerrarSesion};
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

    public JButton getBtnVerContratistas() {
        return btnVerContratistas;
    }

    public JButton getBtnRegistrarTrabajador() {
        return btnRegistrarTrabajador;
    }

    public JButton getBtnAdministrarUsuarios() {
        return btnAdministrarUsuarios;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
