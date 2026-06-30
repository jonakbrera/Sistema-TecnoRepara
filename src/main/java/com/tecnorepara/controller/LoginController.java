package com.tecnorepara.controller;

import com.tecnorepara.dao.UsuarioDAO;
import com.tecnorepara.model.Usuario;
import com.tecnorepara.security.Session;
import com.tecnorepara.util.AlertUtil;
import com.tecnorepara.validation.ValidatorUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void iniciarSesion() {
        String usuarioTexto = txtUsuario.getText().trim();
        String passwordTexto = txtPassword.getText().trim();

        if (ValidatorUtil.estaVacio(usuarioTexto) || ValidatorUtil.estaVacio(passwordTexto)) {
            lblMensaje.setText("Ingrese usuario y contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.validarLogin(usuarioTexto, passwordTexto);

        if (usuario == null) {
            lblMensaje.setText("Usuario o contraseña incorrectos.");
            return;
        }

        Session.iniciarSesion(
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getRol()
        );

        abrirMenuPrincipal();
    }

    private void abrirMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuPrincipal.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 720);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.setTitle("TecnoRepara - Sistema Integral de Gestión");
            stage.setScene(scene);
            stage.setMinWidth(1000);
            stage.setMinHeight(650);
            stage.centerOnScreen();

        } catch (Exception e) {
            AlertUtil.error("No se pudo abrir el menú principal.");
            e.printStackTrace();
        }
    }
}