package com.tecnorepara.controller;

import com.tecnorepara.dao.UsuarioDAO;
import com.tecnorepara.model.Usuario;
import com.tecnorepara.security.Session;
import com.tecnorepara.util.AlertUtil;
import com.tecnorepara.validation.ValidatorUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtPasswordVisible;
    @FXML private Button btnVerPassword;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private boolean passwordVisible = false;

    @FXML
    public void initialize() {
        txtUsuario.setOnAction(event -> iniciarSesion());
        txtPassword.setOnAction(event -> iniciarSesion());
        txtPasswordVisible.setOnAction(event -> iniciarSesion());

        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());
    }

    @FXML
    private void alternarPassword() {
        passwordVisible = !passwordVisible;

        txtPasswordVisible.setVisible(passwordVisible);
        txtPasswordVisible.setManaged(passwordVisible);

        txtPassword.setVisible(!passwordVisible);
        txtPassword.setManaged(!passwordVisible);

        btnVerPassword.setText(passwordVisible ? "🙈" : "👁");
    }

    @FXML
    private void iniciarSesion() {
        String usuarioTexto = txtUsuario.getText().trim();
        String passwordTexto = txtPassword.getText().trim();

        lblMensaje.setText("");

        if (ValidatorUtil.estaVacio(usuarioTexto) || ValidatorUtil.estaVacio(passwordTexto)) {
            lblMensaje.setText("Ingrese usuario y contraseña.");
            return;
        }

        Usuario usuario = usuarioDAO.validarLogin(usuarioTexto, passwordTexto);

        if (usuario == null) {
            lblMensaje.setText("Usuario o contraseña incorrectos.");
            txtPassword.clear();
            txtPasswordVisible.clear();
            txtPassword.requestFocus();
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