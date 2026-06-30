package com.tecnorepara.controller;

import com.tecnorepara.security.Session;
import com.tecnorepara.util.AlertUtil;
import com.tecnorepara.util.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML private StackPane contenedorPrincipal;
    @FXML private Label lblTitulo;
    @FXML private Label lblSubtitulo;
    @FXML private Label lblUsuarioSesion;
    @FXML private Label lblRolSesion;
    @FXML private Button btnUsuarios;
    @FXML private Button btnCerrarSesion;

    @FXML
    public void initialize() {
        configurarSesion();
        cargarDashboard();
    }

    private void configurarSesion() {
        if (Session.haySesionActiva()) {
            lblUsuarioSesion.setText(Session.getNombreUsuario());
            lblRolSesion.setText(Session.getRolUsuario());
        }

        if (!Session.esAdministrador()) {
            btnUsuarios.setVisible(false);
            btnUsuarios.setManaged(false);
        }
    }

    @FXML
    private void cargarDashboard() {
        lblTitulo.setText("Dashboard");
        lblSubtitulo.setText("Resumen general del sistema");

        VBox dashboard = new VBox(15);
        dashboard.getStyleClass().add("panel");

        Label titulo = new Label("Bienvenido a TecnoRepara");
        titulo.getStyleClass().add("section-title");

        Label texto = new Label("Desde este panel se administran usuarios, clientes, productos, inventario, compras, ventas y reportes.");
        texto.getStyleClass().add("normal-text");

        dashboard.getChildren().addAll(titulo, texto);
        contenedorPrincipal.getChildren().setAll(dashboard);
    }

    @FXML
    private void cargarUsuarios() {
        cargarModuloTemporal("Gestión de Usuarios", "Administrar usuarios, roles y accesos del sistema.");
    }

    @FXML
    private void cargarClientes() {
        cargarFXML(
                "/fxml/Clientes.fxml",
                "Registro de Clientes",
                "Registrar, editar, buscar y administrar clientes."
        );
    }

    @FXML
    private void cargarProductos() {
        cargarModuloTemporal("Gestión de Productos", "Administrar productos, precios, categorías y estado.");
    }

    @FXML
    private void cargarInventario() {
        cargarModuloTemporal("Gestión de Inventario", "Controlar stock, movimientos y alertas de inventario.");
    }

    @FXML
    private void cargarCompras() {
        cargarModuloTemporal("Compras", "Registrar compras, proveedores y actualización de stock.");
    }

    @FXML
    private void cargarVentas() {
        cargarModuloTemporal("Ventas", "Registrar ventas, detalles, totales y métodos de pago.");
    }

    @FXML
    private void cargarReportes() {
        cargarModuloTemporal("Reportes", "Visualizar reportes, estadísticas y movimientos del sistema.");
    }

    @FXML
    private void cerrarSesion() {
        if (!AlertUtil.confirmar("¿Desea cerrar sesión?")) {
            return;
        }

        try {
            Session.cerrarSesion();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 720);
            scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

            Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("TecnoRepara - Login");
            stage.centerOnScreen();

        } catch (Exception e) {
            AlertUtil.error("No se pudo cerrar sesión.");
            e.printStackTrace();
        }
    }

    private void cargarFXML(String ruta, String titulo, String subtitulo) {
        lblTitulo.setText(titulo);
        lblSubtitulo.setText(subtitulo);
        SceneManager.cargarModulo(contenedorPrincipal, ruta);
    }

    private void cargarModuloTemporal(String tituloModulo, String descripcion) {
        lblTitulo.setText(tituloModulo);
        lblSubtitulo.setText(descripcion);

        VBox modulo = new VBox(15);
        modulo.getStyleClass().add("panel");

        Label titulo = new Label(tituloModulo);
        titulo.getStyleClass().add("section-title");

        Label texto = new Label("Módulo pendiente de desarrollo. Aquí se cargará el FXML correspondiente.");
        texto.getStyleClass().add("normal-text");

        modulo.getChildren().addAll(titulo, texto);
        contenedorPrincipal.getChildren().setAll(modulo);
    }
}