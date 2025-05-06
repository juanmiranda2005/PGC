package com.miempresa.Controllers;

import com.miempresa.Vista.VistaEmpresaAdministradora;
import com.miempresa.Vista.VistaLogin;
import com.miempresa.Vista.VistaRegistro;
import com.miempresa.Models.Usuario;
import com.miempresa.Persitencias.BaseDatos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class VistaLoginController {
    private VistaLogin vistaLogin;
    private RegistroControllers registroControllers;
    private Map<String, Usuario> mapaUsuarios;
    public VistaLoginController(VistaLogin vistaLogin) {
        this.vistaLogin = vistaLogin;
        this.registroControllers = new RegistroControllers();

        // Acción al presionar "Registrar"
        this.vistaLogin.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaLogin.getFrame().dispose(); // Cierra la ventana de login
            }
        });

        // Acción al presionar "Iniciar Sesión"
        this.vistaLogin.getBtnIniciarSesion().addActionListener(e -> {
            vistaLogin.getFrame().setVisible(true);
            iniciarSesion();
        });
    }

    public void iniciarSesion() {
        try {
            // Obtener los valores de los campos
            String nombreUsuario = vistaLogin.getTxtUsuario().getText().trim();
            String contrasena = new String(vistaLogin.getTxtContrasena().getPassword()).trim();
            String email = vistaLogin.getTxtEmail().getText().trim();

            // Validación de campos vacíos
            if (nombreUsuario.isEmpty() || contrasena.isEmpty() || email.isEmpty()) {
                // Si algún campo está vacío, muestra un mensaje y no continúa con la lógica de validación
                JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                        "Por favor, complete todos los campos.",
                        "Campos vacíos",
                        JOptionPane.WARNING_MESSAGE);
                return; // Salir de la función si hay campos vacíos
            }

            // Búsqueda de usuario en la base de datos
            Usuario usuario = BaseDatos.buscarUsuario(nombreUsuario);

            if (usuario != null) {
                // Validación de contraseña
                if (!usuario.getPassword().equals(contrasena)) {
                    JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                            "Contraseña incorrecta.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }


                // Validación de correo
                if (!usuario.getCorreoElectronico().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                            "El correo electrónico no coincide.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Inicio de sesión exitoso
                JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                        "Bienvenido " + usuario.getUsername() + " (" + usuario.getTipoUsuario() + ")",
                        "Login Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);

                vistaLogin.getFrame().dispose();

                // Redirección según el tipo de usuario
                switch (usuario.getTipoUsuario()) {
                    case CLIENTE_NATURAL:
                         new com.miempresa.Vista.VistaClienteNatural(vistaLogin,mapaUsuarios);
                        break;
                    case CONTRATISTA:
                        new com.miempresa.Vista.VistaContratista(vistaLogin,mapaUsuarios);
                        break;
                    case TRABAJADOR:
                        new com.miempresa.Vista.VistaTrabajador(vistaLogin,mapaUsuarios);
                        break;
                    case CLIENTE_EMPRESA:
                        new com.miempresa.Vista.VistaClienteEmpresa(vistaLogin,mapaUsuarios);
                        break;
                    case EMPRESA_ADMINISTRADORA:
                        new com.miempresa.Vista.VistaEmpresaAdministradora(vistaLogin,mapaUsuarios);
                        break;
                    default:
                        JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                                "Tipo de usuario no reconocido.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }

            } else {
                // Usuario no encontrado o credenciales incorrectas
                JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                        "Usuario no encontrado o credenciales incorrectas.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(vistaLogin.getFrame(),
                    "Error al iniciar sesión: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
