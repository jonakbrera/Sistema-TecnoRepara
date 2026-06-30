package com.tecnorepara.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class SceneManager {

    private SceneManager() {
    }

    public static void cargarModulo(StackPane contenedor, String rutaFXML) {
        try {
            Node vista = FXMLLoader.load(SceneManager.class.getResource(rutaFXML));
            contenedor.getChildren().setAll(vista);
        } catch (Exception e) {
            e.printStackTrace();
            AlertUtil.error("No se pudo cargar la vista: " + rutaFXML);
        }
    }
}