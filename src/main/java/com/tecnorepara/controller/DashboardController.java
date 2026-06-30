package com.tecnorepara.controller;

import com.tecnorepara.dao.DashboardDAO;
import com.tecnorepara.dto.DashboardResumen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.util.Locale;

public class DashboardController {

    @FXML private Label lblClientes;
    @FXML private Label lblProductos;
    @FXML private Label lblVentasHoy;
    @FXML private Label lblStockBajo;

    private final DashboardDAO dashboardDAO = new DashboardDAO();

    @FXML
    public void initialize() {
        cargarResumen();
    }

    private void cargarResumen() {
        DashboardResumen resumen = dashboardDAO.obtenerResumen();

        lblClientes.setText(String.valueOf(resumen.getTotalClientes()));
        lblProductos.setText(String.valueOf(resumen.getTotalProductos()));
        lblStockBajo.setText(String.valueOf(resumen.getStockBajo()));
        lblVentasHoy.setText("Gs. " + formatearNumero(resumen.getVentasHoy()));
    }

    private String formatearNumero(double valor) {
        NumberFormat formato = NumberFormat.getNumberInstance(new Locale("es", "PY"));
        formato.setMaximumFractionDigits(0);
        return formato.format(valor);
    }
}