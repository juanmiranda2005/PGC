package com.miempresa.Vista;

import com.miempresa.Models.Proyecto;
import com.miempresa.Models.Usuario;
import com.miempresa.Persitencias.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class VistaClienteEmpresa {

    private JFrame frame;
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private JButton btnPublicarProyecto, btnVerPostulaciones, btnSeleccionarContratista,
            btnGenerarContrato, btnCerrarSesion, btnHistorialProyectos;

    private Map<String, Usuario> mapaUsuarios;
    private VistaLogin vistaLogin;
    private JTextField txtNombreProyecto, txtPresupuestoProyecto, txtFechaLimiteProyecto;
    private JTextArea txtDescripcionProyecto;
    private JButton btnPublicar;

    public VistaClienteEmpresa(VistaLogin vistaLogin, Map<String, Usuario> mapaUsuarios) {
        this.vistaLogin = vistaLogin;
        this.mapaUsuarios = mapaUsuarios;

        frame = new JFrame("Panel Cliente Empresa");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        Color negro = new Color(30, 30, 30);
        Color azul = new Color(0, 123, 255);
        Color azulHover = azul.brighter();
        Color naranja = new Color(255, 115, 0);
        Color naranjaHover = naranja.brighter();
        Color fondoContenido = new Color(245, 245, 245);

        JPanel panelMenu = new JPanel(new GridLayout(6, 1, 6, 6));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panelMenu.setBackground(negro);

        btnPublicarProyecto = crearBotonMenu("Publicar Proyecto", azul, azulHover);
        btnVerPostulaciones = crearBotonMenu("Ver Postulaciones", azul, azulHover);
        btnSeleccionarContratista = crearBotonMenu("Seleccionar Contratista", azul, azulHover);
        btnGenerarContrato = crearBotonMenu("Generar Contrato", azul, azulHover);
        btnHistorialProyectos = crearBotonMenu("Historial de Proyectos", azul, azulHover);
        btnCerrarSesion = crearBotonMenu("Cerrar Sesión", naranja, naranjaHover);

        panelMenu.add(btnPublicarProyecto);
        panelMenu.add(btnVerPostulaciones);
        panelMenu.add(btnSeleccionarContratista);
        panelMenu.add(btnGenerarContrato);
        panelMenu.add(btnHistorialProyectos);
        panelMenu.add(btnCerrarSesion);

        panelContenido = new JPanel();
        cardLayout = new CardLayout();
        panelContenido.setLayout(cardLayout);

        panelContenido.add(crearPanelPublicarProyecto(), "Publicar Proyecto");
        panelContenido.add(new VistaHistorialProyectos().getPanel(), "Historial Proyectos");
        panelContenido.add(crearPanelContenido("Formulario para generar contrato.", fondoContenido), "Generar Contrato");

        btnPublicarProyecto.addActionListener(e -> cardLayout.show(panelContenido, "Publicar Proyecto"));
        btnVerPostulaciones.addActionListener(e -> cardLayout.show(panelContenido, "Ver Postulaciones"));
        btnSeleccionarContratista.addActionListener(e -> cardLayout.show(panelContenido, "Seleccionar Contratista"));
        btnGenerarContrato.addActionListener(e -> cardLayout.show(panelContenido, "Generar Contrato"));

        btnHistorialProyectos.addActionListener(e -> {
            panelContenido.remove(panelContenido.getComponent(1));
            panelContenido.add(new VistaHistorialProyectos().getPanel(), "Historial Proyectos");
            cardLayout.show(panelContenido, "Historial Proyectos");
            panelContenido.revalidate();
            panelContenido.repaint();
        });

        btnCerrarSesion.addActionListener(e -> {
            frame.dispose();
            vistaLogin.getFrame().setVisible(true);
        });

        frame.add(panelMenu, BorderLayout.WEST);
        frame.add(panelContenido, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel crearPanelPublicarProyecto() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Formulario para publicar proyecto", JLabel.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        txtNombreProyecto = new JTextField();
        txtDescripcionProyecto = new JTextArea(3, 20);
        txtPresupuestoProyecto = new JTextField();
        txtFechaLimiteProyecto = new JTextField();
        btnPublicar = new JButton("Publicar");

        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombreProyecto);
        formPanel.add(new JLabel("Descripción:"));
        formPanel.add(new JScrollPane(txtDescripcionProyecto));
        formPanel.add(new JLabel("Presupuesto:"));
        formPanel.add(txtPresupuestoProyecto);
        formPanel.add(new JLabel("Fecha límite (YYYY-MM-DD):"));
        formPanel.add(txtFechaLimiteProyecto);
        formPanel.add(new JLabel(""));
        formPanel.add(btnPublicar);

        btnPublicar.addActionListener(e -> {
            String nombre = txtNombreProyecto.getText().trim();
            String descripcion = txtDescripcionProyecto.getText().trim();
            String presupuestoStr = txtPresupuestoProyecto.getText().trim();
            String fechaLimite = txtFechaLimiteProyecto.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || presupuestoStr.isEmpty() || fechaLimite.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!fechaLimite.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa AAAA-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double presupuesto;
            try {
                presupuesto = Double.parseDouble(presupuestoStr);
                if (presupuesto <= 0) {
                    JOptionPane.showMessageDialog(null, "El presupuesto debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Presupuesto inválido. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Proyecto nuevoProyecto = new Proyecto(nombre, descripcion, presupuesto, fechaLimite);
            BaseDatos.agregarProyecto(nuevoProyecto);
            JOptionPane.showMessageDialog(null, "Proyecto publicado correctamente.");

            // Limpiar campos
            txtNombreProyecto.setText("");
            txtDescripcionProyecto.setText("");
            txtPresupuestoProyecto.setText("");
            txtFechaLimiteProyecto.setText("");
        });

        panel.add(formPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelContenido(String texto, Color colorFondo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(colorFondo);
        JLabel label = new JLabel(texto, JLabel.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel.add(label, BorderLayout.CENTER);
        return panel;
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

    public JFrame getFrame() {
        return frame;
    }
}
