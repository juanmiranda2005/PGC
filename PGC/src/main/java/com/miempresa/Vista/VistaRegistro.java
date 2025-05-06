package com.miempresa.Vista;

import com.miempresa.Controllers.RegistroControllers;
import com.miempresa.Models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaRegistro {
    private JFrame frame;
    private JTextField txtUsuario, txtEmail, txtNombre, txtApellido, txtEdad;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbGenero, cmbTipoUsuario;
    private JButton btnRegistrar, btnCancelar;
    private VistaLogin vistaLogin;
    private RegistroControllers registroControllers;

    public VistaRegistro(VistaLogin vistaLogin, RegistroControllers registroControllers) {
        this.vistaLogin = vistaLogin;
        this.registroControllers = registroControllers;

        frame = new JFrame("REGISTRO USUARIO DE LA PGC");
        frame.setSize(700, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Colores más corporativos
        Color fondoFormulario = new Color(32, 45, 58); // Azul oscuro (corporativo)
        Color colorTexto = Color.WHITE; // Blanco para el texto
        Color colorBotonPrincipal = new Color(52, 152, 219); // Azul claro y brillante
        Color bordeCampo = new Color(189, 195, 199); // Gris claro para bordes

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(fondoFormulario);
        GridBagConstraints gbc = new GridBagConstraints();

        // Fondo de cuadro más claro
        JPanel cuadroFormulario = new JPanel(new BorderLayout());
        cuadroFormulario.setBackground(new Color(236, 240, 241));
        cuadroFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        cuadroFormulario.setPreferredSize(new Dimension(500, 650));
        cuadroFormulario.setMaximumSize(new Dimension(500, 650));

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new BoxLayout(panelFormulario, BoxLayout.Y_AXIS));
        panelFormulario.setBackground(new Color(236, 240, 241)); // Gris claro

        Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 14);

        panelFormulario.add(crearCampo("Usuario*", txtUsuario = new JTextField(), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Correo*", txtEmail = new JTextField(), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Contraseña*", txtContrasena = new JPasswordField(), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Nombre*", txtNombre = new JTextField(), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Apellido*", txtApellido = new JTextField(), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Edad*", txtEdad = new JTextField(), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Género*", cmbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino", "Otro"}), fuenteCampos, bordeCampo));
        panelFormulario.add(crearCampo("Tipo de Usuario*", cmbTipoUsuario = new JComboBox<>(new String[]{
                "Cliente Empresa", "Cliente Natural", "Contratista", "Empresa Administradora", "Trabajador"
        }), fuenteCampos, bordeCampo));

        cuadroFormulario.add(panelFormulario, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(new Color(236, 240, 241)); // Gris claro

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRegistrar.setBackground(colorBotonPrincipal); // Azul brillante
        btnRegistrar.setForeground(Color.WHITE); // Texto blanco
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setPreferredSize(new Dimension(130, 35));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCancelar.setBackground(new Color(189, 195, 199)); // Gris claro
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setPreferredSize(new Dimension(130, 35));
        btnCancelar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
                frame.dispose();
                vistaLogin.getFrame().setVisible(true);
            }
        });

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        cuadroFormulario.add(panelBotones, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5, 0, 0, 0); // Margen superior
        panelPrincipal.add(cuadroFormulario, gbc);

        frame.setContentPane(panelPrincipal);
        frame.setVisible(true);
    }

    private JPanel crearCampo(String etiqueta, JComponent campo, Font fuente, Color bordeColor) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(Color.BLACK); // Texto negro para el label
        campo.setFont(fuente);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campo.setBorder(BorderFactory.createLineBorder(bordeColor));

        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.setBackground(new Color(236, 240, 241)); // Fondo gris claro para el contenedor
        contenedor.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        contenedor.add(label);
        contenedor.add(Box.createVerticalStrut(3));
        contenedor.add(campo);
        return contenedor;
    }

    private void registrarUsuario() {
        String usuario = txtUsuario.getText();
        String email = txtEmail.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String edadTexto = txtEdad.getText();
        String genero = (String) cmbGenero.getSelectedItem();
        String tipoUsuarioStr = (String) cmbTipoUsuario.getSelectedItem();

        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertir tipo de usuario (String) a enum Usuario.TipoUsuario
        Usuario.TipoUsuario tipoUsuario;
        try {
            tipoUsuario = Usuario.TipoUsuario.valueOf(tipoUsuarioStr.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(frame, "Tipo de usuario no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = registroControllers.registrarUsuario(
                usuario, contrasena, tipoUsuario,
                nombre, apellido, email, edad
        );

        if (exito) {
            limpiarCampos();
            frame.dispose();
            vistaLogin.getFrame().setVisible(true);
        }
    }

    private void limpiarCampos() {
        txtUsuario.setText("");
        txtEmail.setText("");
        txtContrasena.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        cmbGenero.setSelectedIndex(0);
        cmbTipoUsuario.setSelectedIndex(0);
    }

    public JFrame getFrame() {
        return frame;
    }
}
