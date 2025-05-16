package com.miempresa.Vista;

import com.miempresa.Models.Proyecto;
import com.miempresa.Models.Usuario;
import com.miempresa.Persitencias.BaseDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class VistaContratista {

    private JFrame frame;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    private JButton btnVerProyectos, btnPostularse, btnActualizarEstadoContrato, btnCerrarSesion;

    private VistaLogin vistaLogin;
    private Usuario usuarioContratista;

    private Map<String, Usuario> mapaUsuarios;

    // Componentes para Ver Proyectos
    private JTable tablaProyectos;
    private DefaultTableModel modeloTablaProyectos;

    // Componentes para Postularse
    private JComboBox<String> comboProyectosPostular;
    private JTextArea txtMensajePostulacion;
    private JButton btnEnviarPostulacion;

    // Componentes para Actualizar Estado
    private JComboBox<String> comboContratos;
    private JComboBox<String> comboNuevoEstado;
    private JButton btnActualizarEstado;

    public VistaContratista(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;
        this.usuarioContratista = usuarioContratista;
        frame = new JFrame("Panel Contratista");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        Color negro = new Color(30, 30, 30);
        Color azul = new Color(0, 123, 255);
        Color azulHover = azul.brighter();
        Color rojo = new Color(220, 53, 69);
        Color rojoHover = rojo.brighter();

        JPanel panelMenu = new JPanel(new GridLayout(5, 1, 6, 6));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelMenu.setBackground(negro);

        btnVerProyectos = crearBotonMenu("Ver Proyectos", azul, azulHover);
        btnPostularse = crearBotonMenu("Postularse", azul, azulHover);
        btnActualizarEstadoContrato = crearBotonMenu("Actualizar Estado", azul, azulHover);
        btnCerrarSesion = crearBotonMenu("Cerrar Sesión", rojo, rojoHover);

        panelMenu.add(btnVerProyectos);
        panelMenu.add(btnPostularse);
        panelMenu.add(btnActualizarEstadoContrato);
        panelMenu.add(btnCerrarSesion);

        panelContenido = new JPanel();
        cardLayout = new CardLayout();
        panelContenido.setLayout(cardLayout);

        panelContenido.add(crearPanelVerProyectos(), "Ver Proyectos");
        panelContenido.add(crearPanelPostularse(), "Postularse");
        panelContenido.add(crearPanelActualizarEstado(), "Actualizar Estado");

        btnVerProyectos.addActionListener(e -> {
            cargarProyectosEnTabla();
            cargarProyectosEnCombo();
            cardLayout.show(panelContenido, "Ver Proyectos");
        });

        btnPostularse.addActionListener(e -> {
            cargarProyectosEnCombo();
            cardLayout.show(panelContenido, "Postularse");
        });

        btnActualizarEstadoContrato.addActionListener(e -> {
            cargarContratosEnCombo();
            cardLayout.show(panelContenido, "Actualizar Estado");
        });

        btnCerrarSesion.addActionListener(e -> {
            frame.dispose();
            vistaLogin.getFrame().setVisible(true);
        });

        frame.add(panelMenu, BorderLayout.WEST);
        frame.add(panelContenido, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // PANEL: Ver Proyectos
    private JPanel crearPanelVerProyectos() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Proyectos Disponibles", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Descripción", "Presupuesto", "Fecha Límite"};
        modeloTablaProyectos = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla no editable
            }
        };
        tablaProyectos = new JTable(modeloTablaProyectos);
        JScrollPane scroll = new JScrollPane(tablaProyectos);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void cargarProyectosEnTabla() {
        modeloTablaProyectos.setRowCount(0);
        List<Proyecto> proyectos = BaseDatos.getProyectos();
        for (Proyecto p : proyectos) {
            Object[] fila = {
                    p.getId(),
                    p.getNombre(),
                    p.getDescripcion(),
                    p.getPresupuesto(),
                    p.getFechaLimite()
            };
            modeloTablaProyectos.addRow(fila);
        }
    }

    // PANEL: Postularse
    private JPanel crearPanelPostularse() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Postularse a un Proyecto", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblProyecto = new JLabel("Proyecto:");
        comboProyectosPostular = new JComboBox<>();

        JLabel lblMensaje = new JLabel("Mensaje de Postulación:");
        txtMensajePostulacion = new JTextArea(5, 30);
        txtMensajePostulacion.setLineWrap(true);
        txtMensajePostulacion.setWrapStyleWord(true);
        JScrollPane scrollMensaje = new JScrollPane(txtMensajePostulacion);

        btnEnviarPostulacion = new JButton("Enviar Postulación");

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(lblProyecto, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(comboProyectosPostular, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        formPanel.add(lblMensaje, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(scrollMensaje, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnEnviarPostulacion, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        btnEnviarPostulacion.addActionListener(e -> {
            String proyectoSeleccionado = (String) comboProyectosPostular.getSelectedItem();
            String mensaje = txtMensajePostulacion.getText().trim();

            if (proyectoSeleccionado == null || proyectoSeleccionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Seleccione un proyecto para postularse.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (mensaje.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ingrese un mensaje para la postulación.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar usuarioContratista no nulo
            if (usuarioContratista == null) {
                JOptionPane.showMessageDialog(frame, "Error: usuario no identificado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Extraer solo el ID del proyecto que está en formato "ID - Nombre"
            String id = proyectoSeleccionado.split(" - ")[0].trim();
            String usernameContratista = usuarioContratista.getUsername();

            // Agregar la postulación en BaseDatos
            boolean exito = BaseDatos.agregarPostulacion(id, usernameContratista, mensaje);

            if (exito) {
                JOptionPane.showMessageDialog(frame, "Postulación enviada correctamente.");
                txtMensajePostulacion.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Error al enviar la postulación.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private void cargarProyectosEnCombo() {
        comboProyectosPostular.removeAllItems();
        List<Proyecto> proyectos = BaseDatos.getProyectos();
        for (Proyecto p : proyectos) {
            comboProyectosPostular.addItem(p.getId() + " - " + p.getNombre());
        }
    }

    // PANEL: Actualizar Estado
    private JPanel crearPanelActualizarEstado() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("Actualizar Estado de Contrato", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblContrato = new JLabel("Contrato:");
        comboContratos = new JComboBox<>();

        JLabel lblNuevoEstado = new JLabel("Nuevo Estado:");
        comboNuevoEstado = new JComboBox<>(new String[]{"En proceso", "Finalizado", "Cancelado"});

        btnActualizarEstado = new JButton("Actualizar Estado");

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(lblContrato, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(comboContratos, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(lblNuevoEstado, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(comboNuevoEstado, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnActualizarEstado, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        btnActualizarEstado.addActionListener(e -> {
            String contratoSeleccionado = (String) comboContratos.getSelectedItem();
            String nuevoEstado = (String) comboNuevoEstado.getSelectedItem();

            if (contratoSeleccionado == null) {
                JOptionPane.showMessageDialog(frame, "Seleccione un contrato.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nuevoEstado == null || nuevoEstado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Seleccione un nuevo estado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(frame, "Estado del contrato actualizado correctamente.");
        });

        return panel;
    }

    private void cargarContratosEnCombo() {
        comboContratos.removeAllItems();

        for (Proyecto p : BaseDatos.getProyectosDisponibles()) {
            comboContratos.addItem(p.getId() + " - " + p.getNombre());
        }
    }


    private JButton crearBotonMenu(String texto, Color color, Color colorHover) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setBorderPainted(false);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(color);
            }
        });

        return boton;
    }

    public JFrame getFrame() {
        return frame;
    }
}
