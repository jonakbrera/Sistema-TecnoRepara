package com.tecnorepara.dto;

public class DashboardResumen {

    private int totalClientes;
    private int totalProductos;
    private int stockBajo;
    private double ventasHoy;

    public DashboardResumen(int totalClientes, int totalProductos, int stockBajo, double ventasHoy) {
        this.totalClientes = totalClientes;
        this.totalProductos = totalProductos;
        this.stockBajo = stockBajo;
        this.ventasHoy = ventasHoy;
    }

    public int getTotalClientes() {
        return totalClientes;
    }

    public int getTotalProductos() {
        return totalProductos;
    }

    public int getStockBajo() {
        return stockBajo;
    }

    public double getVentasHoy() {
        return ventasHoy;
    }
}