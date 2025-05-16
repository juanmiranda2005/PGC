package com.miempresa.Vista;

import com.miempresa.Models.Proyecto;
import com.miempresa.Persitencias.BaseDatos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaHistorialProyectos extends JPanel {

    private JTable tablaProyectos;
    private DefaultTableModel modeloTabla;

    public VistaHistorialProyectos() {
        setLayout(new BorderLayout());
        setName("Historial Proyectos");

        String[] columnas = {"ID", "Nombre", "Descripción", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProyectos = new JTable(modeloTabla);

        cargarDatosProyectos();

        add(new JScrollPane(tablaProyectos), BorderLayout.CENTER);
    }

    private void cargarDatosProyectos() {
        List<Proyecto> proyectos = BaseDatos.getTodosProyectos();

        modeloTabla.setRowCount(0);

        if (proyectos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay proyectos publicados.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (Proyecto p : proyectos) {
                Object[] fila = {
                        p.getId(),
                        p.getNombre(),
                        p.getDescripcion(),
                        p.getEstado(),
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    public JPanel getPanel() {
        return this;
    }
}
