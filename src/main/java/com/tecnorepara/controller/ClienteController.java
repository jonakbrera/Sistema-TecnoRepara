package com.tecnorepara.controller;

import com.tecnorepara.constants.Mensajes;
import com.tecnorepara.dao.ClienteDAO;
import com.tecnorepara.model.Cliente;
import com.tecnorepara.util.AlertUtil;
import com.tecnorepara.validation.ValidatorUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ClienteController {

    @FXML private TextField txtBuscar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TextField txtDireccion;
    @FXML private CheckBox chkEstado;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colDocumento;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colDireccion;
    @FXML private TableColumn<Cliente, String> colEstado;

    private final ClienteDAO clienteDAO = new ClienteDAO();
    private ObservableList<Cliente> listaClientes;
    private Cliente clienteSeleccionado;

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarTabla();
        configurarSeleccionTabla();
    }

    private void configurarColumnas() {
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getIdCliente()).asObject());
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colDocumento.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDocumento()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colDireccion.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDireccion()));
        colEstado.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEstadoTexto()));
    }

    @FXML
    public void cargarTabla() {
        listaClientes = FXCollections.observableArrayList(clienteDAO.listar());
        tablaClientes.setItems(listaClientes);
    }

    private void configurarSeleccionTabla() {
        tablaClientes.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && tablaClientes.getSelectionModel().getSelectedItem() != null) {
                clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
                cargarDatosFormulario(clienteSeleccionado);
            }
        });
    }

    private void cargarDatosFormulario(Cliente cliente) {
        txtNombre.setText(cliente.getNombre());
        txtDocumento.setText(cliente.getDocumento());
        txtTelefono.setText(cliente.getTelefono());
        txtEmail.setText(cliente.getEmail());
        txtDireccion.setText(cliente.getDireccion());
        chkEstado.setSelected(cliente.isEstado());
    }

    @FXML
    private void guardar() {
        if (!validarCampos()) return;

        Cliente cliente = new Cliente();
        cliente.setNombre(txtNombre.getText().trim());
        cliente.setDocumento(txtDocumento.getText().trim());
        cliente.setTelefono(txtTelefono.getText().trim());
        cliente.setEmail(txtEmail.getText().trim());
        cliente.setDireccion(txtDireccion.getText().trim());
        cliente.setEstado(chkEstado.isSelected());

        if (clienteDAO.insertar(cliente)) {
            AlertUtil.exito(Mensajes.REGISTRO_GUARDADO);
            cargarTabla();
            limpiar();
        } else {
            AlertUtil.error(Mensajes.ERROR_GUARDAR);
        }
    }

    @FXML
    private void editar() {
        if (clienteSeleccionado == null) {
            AlertUtil.advertencia(Mensajes.SELECCIONE_REGISTRO);
            return;
        }

        if (!validarCampos()) return;

        clienteSeleccionado.setNombre(txtNombre.getText().trim());
        clienteSeleccionado.setDocumento(txtDocumento.getText().trim());
        clienteSeleccionado.setTelefono(txtTelefono.getText().trim());
        clienteSeleccionado.setEmail(txtEmail.getText().trim());
        clienteSeleccionado.setDireccion(txtDireccion.getText().trim());
        clienteSeleccionado.setEstado(chkEstado.isSelected());

        if (clienteDAO.actualizar(clienteSeleccionado)) {
            AlertUtil.exito(Mensajes.REGISTRO_ACTUALIZADO);
            cargarTabla();
            limpiar();
        } else {
            AlertUtil.error(Mensajes.ERROR_ACTUALIZAR);
        }
    }

    @FXML
    private void eliminar() {
        if (clienteSeleccionado == null) {
            AlertUtil.advertencia(Mensajes.SELECCIONE_REGISTRO);
            return;
        }

        if (AlertUtil.confirmar(Mensajes.CONFIRMAR_ELIMINAR)) {
            if (clienteDAO.eliminar(clienteSeleccionado.getIdCliente())) {
                AlertUtil.exito(Mensajes.REGISTRO_ELIMINADO);
                cargarTabla();
                limpiar();
            } else {
                AlertUtil.error(Mensajes.ERROR_ELIMINAR);
            }
        }
    }

    @FXML
    private void buscar() {
        String filtro = txtBuscar.getText().trim().toLowerCase();

        if (ValidatorUtil.estaVacio(filtro)) {
            cargarTabla();
            return;
        }

        ObservableList<Cliente> filtrados = listaClientes.filtered(cliente ->
                safe(cliente.getNombre()).contains(filtro)
                        || safe(cliente.getDocumento()).contains(filtro)
                        || safe(cliente.getTelefono()).contains(filtro)
                        || safe(cliente.getEmail()).contains(filtro)
        );

        tablaClientes.setItems(filtrados);
    }

    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtDocumento.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtDireccion.clear();
        txtBuscar.clear();
        chkEstado.setSelected(true);
        clienteSeleccionado = null;
        tablaClientes.getSelectionModel().clearSelection();
    }

    private boolean validarCampos() {
        if (ValidatorUtil.estaVacio(txtNombre.getText())) {
            AlertUtil.advertencia("El nombre del cliente es obligatorio.");
            txtNombre.requestFocus();
            return false;
        }

        if (!ValidatorUtil.soloLetras(txtNombre.getText().trim())) {
            AlertUtil.advertencia("El nombre no puede contener números ni símbolos.");
            txtNombre.requestFocus();
            return false;
        }

        if (!ValidatorUtil.esEmailValido(txtEmail.getText().trim())) {
            AlertUtil.advertencia("Ingrese un correo electrónico válido.");
            txtEmail.requestFocus();
            return false;
        }

        return true;
    }

    private String safe(String texto) {
        return texto == null ? "" : texto.toLowerCase();
    }
}