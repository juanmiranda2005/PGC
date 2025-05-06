package com.miempresa.Vista;

import com.miempresa.Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class VistaClienteEmpresa {

    private JFrame frame;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    private JButton btnPublicarProyecto, btnVerPostulaciones, btnSeleccionarContratista, btnGenerarContrato, btnCerrarSesion;

    private Map<String, Usuario> mapaUsuarios;
    private VistaLogin vistaLogin;

    public VistaClienteEmpresa(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;

        frame = new JFrame("Panel Cliente Empresa");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Colores elegantes y profesionales
        Color negro = new Color(30, 30, 30);
        Color azul = new Color(0, 123, 255);
        Color azulHover = azul.brighter();
        Color naranja = new Color(255, 115, 0);
        Color naranjaHover = naranja.brighter();
        Color fondoContenido = new Color(245, 245, 245); // Gris claro suave

        // Panel lateral de menú
        JPanel panelMenu = new JPanel(new GridLayout(6, 1, 6, 6));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelMenu.setBackground(negro);

        // Botones
        btnPublicarProyecto = crearBotonMenu("Publicar Proyecto", azul, azulHover);
        btnVerPostulaciones = crearBotonMenu("Ver Postulaciones", azul, azulHover);
        btnSeleccionarContratista = crearBotonMenu("Seleccionar Contratista", azul, azulHover);
        btnGenerarContrato = crearBotonMenu("Generar Contrato", azul, azulHover);
        btnCerrarSesion = crearBotonMenu("Cerrar Sesión", naranja, naranjaHover);

        // Añadir al menú
        panelMenu.add(btnPublicarProyecto);
        panelMenu.add(btnVerPostulaciones);
        panelMenu.add(btnSeleccionarContratista);
        panelMenu.add(btnGenerarContrato);
        panelMenu.add(btnCerrarSesion);

        // Panel de contenido
        panelContenido = new JPanel();
        cardLayout = new CardLayout();
        panelContenido.setLayout(cardLayout);

        panelContenido.add(crearPanelContenido("Formulario para publicar un proyecto.", fondoContenido), "Publicar Proyecto");
        panelContenido.add(crearPanelContenido("Lista de postulaciones recibidas.", fondoContenido), "Ver Postulaciones");
        panelContenido.add(crearPanelContenido("Pantalla para seleccionar un contratista.", fondoContenido), "Seleccionar Contratista");
        panelContenido.add(crearPanelContenido("Formulario para generar contrato.", fondoContenido), "Generar Contrato");

        // Acciones
        btnPublicarProyecto.addActionListener(e -> cardLayout.show(panelContenido, "Publicar Proyecto"));
        btnVerPostulaciones.addActionListener(e -> cardLayout.show(panelContenido, "Ver Postulaciones"));
        btnSeleccionarContratista.addActionListener(e -> cardLayout.show(panelContenido, "Seleccionar Contratista"));
        btnGenerarContrato.addActionListener(e -> cardLayout.show(panelContenido, "Generar Contrato"));
        btnCerrarSesion.addActionListener(e -> {
            frame.dispose();
            vistaLogin.getFrame().setVisible(true);
        });

        frame.add(panelMenu, BorderLayout.WEST);
        frame.add(panelContenido, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JButton crearBotonMenu(String texto, Color colorFondo, Color colorHover) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setFocusPainted(false);
        btn.setBackground(colorFondo);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(colorFondo);
            }
        });

        return btn;
    }

    private JPanel crearPanelContenido(String texto, Color colorFondo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorFondo);
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnPublicarProyecto() {
        return btnPublicarProyecto;
    }

    public JButton getBtnVerPostulaciones() {
        return btnVerPostulaciones;
    }

    public JButton getBtnSeleccionarContratista() {
        return btnSeleccionarContratista;
    }

    public JButton getBtnGenerarContrato() {
        return btnGenerarContrato;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }
}
