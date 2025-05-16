package com.miempresa.Vista;

import com.miempresa.Models.Postulacion;
import com.miempresa.Models.Proyecto;
import com.miempresa.Persitencias.BaseDatos;

import javax.swing.*;
import java.awt.*;

public class VistaSeleccionarContratista extends JPanel {

    public VistaSeleccionarContratista() {
        setLayout(new BorderLayout());
        setName("Seleccionar Contratista");

        JComboBox<Proyecto> comboProyectos = new JComboBox<>();
        for (Proyecto p : BaseDatos.getTodosProyectos()) {
            comboProyectos.addItem(p);
        }

        DefaultListModel<Postulacion> model = new DefaultListModel<>();
        JList<Postulacion> listaPostulaciones = new JList<>(model);
        listaPostulaciones.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Postulacion p) {
                    setText("Contratista: " + p.getNombreContratista() + " | Estado: " + p.getEstado());
                }
                return this;
            }
        });
        JScrollPane scroll = new JScrollPane(listaPostulaciones);

        JButton btnCargar = new JButton("Cargar Postulaciones");
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnRechazar = new JButton("Rechazar");

        // Cargar postulaciones al seleccionar un proyecto
        btnCargar.addActionListener(e -> {
            Proyecto seleccionado = (Proyecto) comboProyectos.getSelectedItem();
            model.clear();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Seleccione un proyecto primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (seleccionado.getPostulaciones().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Este proyecto no tiene postulaciones.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            for (Postulacion p : seleccionado.getPostulaciones()) {
                model.addElement(p);
            }
        });

        // Aceptar contratista
        btnAceptar.addActionListener(e -> {
            Postulacion seleccionada = listaPostulaciones.getSelectedValue();
            if (seleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una postulación para aceptar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de aceptar al contratista?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                seleccionada.setEstado("Aceptado");
                listaPostulaciones.repaint();
                JOptionPane.showMessageDialog(this, "Contratista aceptado correctamente.");
            }
        });

        // Rechazar contratista
        btnRechazar.addActionListener(e -> {
            Postulacion seleccionada = listaPostulaciones.getSelectedValue();
            if (seleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una postulación para rechazar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de rechazar al contratista?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                seleccionada.setEstado("Rechazado");
                listaPostulaciones.repaint();
                JOptionPane.showMessageDialog(this, "Contratista rechazado correctamente.");
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(comboProyectos, BorderLayout.CENTER);
        topPanel.add(btnCargar, BorderLayout.EAST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnAceptar);
        bottomPanel.add(btnRechazar);

        add(topPanel, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }
}
