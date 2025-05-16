package com.miempresa.Vista;
import com.miempresa.Controllers.VistaLoginController;
import com.miempresa.Persitencias.BaseDatos;


public class Main {
    public static void main(String[] args) {
        VistaLogin vista = new VistaLogin(BaseDatos.mapaUsuarios);
        new VistaLoginController(vista);
    }
}
