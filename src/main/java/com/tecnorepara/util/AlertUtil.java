package com.tecnorepara.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertUtil {

    public static void exito(String mensaje) {
        mostrar("Éxito", mensaje, Alert.AlertType.INFORMATION);
    }

    public static void error(String mensaje) {
        mostrar("Error", mensaje, Alert.AlertType.ERROR);
    }

    public static void advertencia(String mensaje) {
        mostrar("Atención", mensaje, Alert.AlertType.WARNING);
    }

    public static boolean confirmar(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmar acción");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }

    private static void mostrar(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}