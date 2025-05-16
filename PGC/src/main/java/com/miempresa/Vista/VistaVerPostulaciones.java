package com.miempresa.Vista;

import com.miempresa.Models.Postulacion;
import com.miempresa.Models.Proyecto;
import com.miempresa.Persitencias.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VistaVerPostulaciones extends JPanel {

    private JComboBox<Proyecto> comboProyectos;
    private DefaultListModel<Postulacion> modelPostulaciones;
    private JList<Postulacion> listaPostulaciones;
    private JButton btnCargar, btnAceptar, btnRechazar;

    public VistaVerPostulaciones() {
        setLayout(new BorderLayout());
        setName("Ver Postulaciones");

        comboProyectos = new JComboBox<>();
        modelPostulaciones = new DefaultListModel<>();
        listaPostulaciones = new JList<>(modelPostulaciones);

        // Renderer para mostrar texto personalizado
        listaPostulaciones.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Postulacion p) {
                    setText("Contratista: " + p.getNombreContratista() + " | Estado: " + p.getEstado());
                }
                return this;
            }
        });

        JScrollPane scroll = new JScrollPane(listaPostulaciones);

        btnCargar = new JButton("Cargar Postulaciones");
        btnAceptar = new JButton("Aceptar");
        btnRechazar = new JButton("Rechazar");

        // Panel superior: combo y botón cargar
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(new JLabel("Seleccione un proyecto:"), BorderLayout.NORTH);
        JPanel comboPanel = new JPanel(new BorderLayout());
        comboPanel.add(comboProyectos, BorderLayout.CENTER);
        comboPanel.add(btnCargar, BorderLayout.EAST);
        panelSuperior.add(comboPanel, BorderLayout.CENTER);

        // Panel inferior: botones aceptar y rechazar
        JPanel panelInferior = new JPanel();
        panelInferior.add(btnAceptar);
        panelInferior.add(btnRechazar);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        cargarProyectosEnCombo();

        // Eventos
        btnCargar.addActionListener(e -> cargarPostulaciones());
        Proyecto proyectoSeleccionado = (Proyecto) comboProyectos.getSelectedItem();
        Postulacion postulacionSeleccionada = listaPostulaciones.getSelectedValue();

        btnAceptar.addActionListener(e -> {
            Postulacion seleccionada = listaPostulaciones.getSelectedValue();
            if (seleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una postulación para aceptar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de aceptar al contratista?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                seleccionada.setEstado("Aceptado");
                proyectoSeleccionado.setEstado("Asignado");
                proyectoSeleccionado.setContratistaAsignado(postulacionSeleccionada.getNombreContratista());

                // Actualizar datos en la base de datos (simulada)
                BaseDatos.actualizarEstadoContrato(proyectoSeleccionado.getId(), "Asignado");
                BaseDatos.asignarContratistaAProyecto(proyectoSeleccionado.getId(), postulacionSeleccionada.getNombreContratista());

                // Actualizar la lista para reflejar cambios
                cargarPostulaciones();

                listaPostulaciones.repaint();
                JOptionPane.showMessageDialog(this, "Contratista aceptado correctamente.");
            }
        });

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
    }

    private void cargarProyectosEnCombo() {
        comboProyectos.removeAllItems();
        List<Proyecto> proyectos = BaseDatos.getTodosProyectos();
        if (proyectos != null && !proyectos.isEmpty()) {
            for (Proyecto p : proyectos) {
                comboProyectos.addItem(p);
            }
        }
    }

    private void cargarPostulaciones() {
        Proyecto seleccionado = (Proyecto) comboProyectos.getSelectedItem();
        modelPostulaciones.clear();

        if (seleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto primero.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<Postulacion> postulaciones = seleccionado.getPostulaciones();

        if (postulaciones == null || postulaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este proyecto no tiene postulaciones.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Postulacion p : postulaciones) {
            modelPostulaciones.addElement(p);
        }
    }

    public JPanel getPanel() {
        return this;
    }
}
