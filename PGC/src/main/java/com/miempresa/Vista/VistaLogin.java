package com.miempresa.Vista;

import com.miempresa.Controllers.RegistroControllers;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.miempresa.Models.Usuario;

import java.awt.*;
import java.net.URI;
import java.util.Map;

public class VistaLogin {
    private JFrame frame;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private boolean contrasenaVisible = false;
    private JTextField txtEmail;
    private Map<String, Usuario> mapaUsuarios;

    private JButton btnIniciarSesion, btnRegistrar, btnOlvidecontraseña,btnWhatsapp,btnFacebook,btnGmail, btnInstagram, btnMostrarContraseña;

    public VistaLogin(Map<String, Usuario> mapaUsuarios) {
        this.mapaUsuarios = mapaUsuarios;
        frame = new JFrame("Inicio de Sesión");
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        RegistroControllers registroControllers = new RegistroControllers();

        FondoPanel fondo = new FondoPanel();
        fondo.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();


        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));


        g2.setColor(new Color(245, 245, 245));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);


        g2.setColor(new Color(200, 200, 200));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);


        g2.setColor(new Color(180, 180, 180));
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);



        g2.dispose();
    }
};
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(500, 450));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 🔹 Etiqueta de título
        JLabel lblTitulo = new JLabel("LOGIN PGC");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(lblTitulo, gbc);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblUsuario, gbc);

        txtUsuario = new JTextField(15);
        txtUsuario.setPreferredSize(new Dimension(250, 35));
        txtUsuario.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtUsuario, gbc);

        JLabel lblEmail = new JLabel("Correo Electrónico:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblEmail, gbc);

        txtEmail = new JTextField(15);
        txtEmail.setPreferredSize(new Dimension(250, 35));
        txtEmail.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtEmail, gbc);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.EAST;
        panel.add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(15);
        txtContrasena.setPreferredSize(new Dimension(250, 35));
        txtContrasena.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        panel.add(txtContrasena, gbc);

        // botton para ocultar y mostrar contraseña
        btnMostrarContraseña = new JButton("👁");
        btnMostrarContraseña.setPreferredSize(new Dimension(30, 30));
        btnMostrarContraseña.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        btnMostrarContraseña.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 2;
        gbc.gridwidth = 2;
        panel.add(btnMostrarContraseña, gbc);
        btnMostrarContraseña.addActionListener(e -> alternarVisibilidadContrasena());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelBotones.setOpaque(false);


        btnRegistrar = new JButton("REGISTRAR");
        estiloBoton(btnRegistrar);
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnRegistrar, gbc);

     btnRegistrar.addActionListener(e -> {
    frame.setVisible(false);
    new VistaRegistro(this, registroControllers);
     });


        btnIniciarSesion = new JButton("INICIAR SESIÓN");
        estiloBoton(btnIniciarSesion);
        gbc.gridx = 1;
        panel.add(btnIniciarSesion, gbc);

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(panelBotones, gbc);


        btnOlvidecontraseña = new JButton("¿OLVIDÓ SU CLAVE?");
        btnOlvidecontraseña.setBorderPainted(false);
        btnOlvidecontraseña.setContentAreaFilled(false);
        btnOlvidecontraseña.setForeground(Color.BLUE);
        btnOlvidecontraseña.setFont(new Font("Arial", Font.PLAIN, 12));
        btnOlvidecontraseña.setCursor(new Cursor(Cursor.HAND_CURSOR));

       gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnOlvidecontraseña, gbc);

        fondo.add(panel);
        frame.setContentPane(fondo);
        frame.setVisible(true);

            // 🔹 botones de redes sociales
            JPanel panelRedes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            panelRedes.setOpaque(false);

            btnWhatsapp = crearBotonConIcono("whatsapp.jpg", "WhatsApp");
            btnFacebook = crearBotonConIcono("facebook.jpg", "Facebook");
            btnGmail = crearBotonConIcono("gmail.jpg", "Gmail");
            btnInstagram = crearBotonConIcono("instagram.jpg", "Instagram");

            panelRedes.add(btnWhatsapp);
            panelRedes.add(btnFacebook);
            panelRedes.add(btnGmail);
            panelRedes.add(btnInstagram);

            gbc.gridy = 4; gbc.gridx = 1; gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            panel.add(panelRedes, gbc);

            fondo.add(panelRedes, gbc);
            frame.setContentPane(fondo);
            frame.setVisible(true);

    // 🔹 Enlace de Política de Privacidad y Términos
    JLabel lblPoliticas = new JLabel("Política de Privacidad y Términos");
    lblPoliticas.setForeground(Color.BLUE);
    lblPoliticas.setFont(new Font("Arial", Font.PLAIN, 12));
    lblPoliticas.setCursor(new Cursor(Cursor.HAND_CURSOR));

    gbc.gridy = 7;
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    panel.add(lblPoliticas, gbc);



        // mover el panel del formulario
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.weightx =1;
        gbc.anchor = GridBagConstraints.CENTER;
        fondo.add(panel, gbc);
        frame.setContentPane(fondo);
        frame.setVisible(true);

                }

        //  crear botones con iconos
    private JButton crearBotonConIcono(String rutaImagen, String tooltip) {
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource(rutaImagen));
        if (icono.getImage() == null) {
            System.err.println("⚠️ No se encontró la imagen: " + rutaImagen);
            return new JButton(tooltip);
        }
        Image img = icono.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        JButton boton = new JButton(new ImageIcon(img));

        boton.setToolTipText(tooltip);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
    }

     // abrir enlaces
    private void abrirEnlace(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JTextField crearCampoTexto(String placeholder) {
        JTextField campo = new JTextField(15);
        campo.setPreferredSize(new Dimension(250, 35));
        campo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return campo;
    }

    private void estiloBoton(JButton boton) {
        boton.setPreferredSize(new Dimension(150, 40));
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
    }

    private void alternarVisibilidadContrasena() {
        if (contrasenaVisible) {
            txtContrasena.setEchoChar('•');
            btnMostrarContraseña.setText("👁");
        } else {
            txtContrasena.setEchoChar((char) 0);
            btnMostrarContraseña.setText("🔒");
        }
        contrasenaVisible = !contrasenaVisible;
    }

    public JPasswordField getTxtContrasena() {
        return txtContrasena;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    //  Clase para manejar la imagen de fondo
class FondoPanel extends JPanel {
    private Color colorFondo;

    public FondoPanel() {
        this.colorFondo = new Color(30, 58, 95); // Azul oscuro (#0D1B2A)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(colorFondo);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
}
