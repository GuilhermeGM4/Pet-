package org.controller.Cliente;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.DAO.Cliente.ClienteDAO;
import org.model.Cliente;

import java.util.ArrayList;
import java.util.Objects;

public class GerenciarClientesController extends Application {
    @FXML
    private Button btnBackwards;

    @FXML
    private Button btnDetails;

    @FXML
    private Button btnRegister;

    @FXML
    private TableColumn<?,?> columnCpf;

    @FXML
    private TableColumn<?,?> columnName;

    @FXML
    private TableColumn<?,?> columnPhone;

    @FXML
    private TableView<Cliente> tableClients;
    private ObservableList<Cliente> clientsList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gerenciar_clientes.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Gerenciar Clientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        fillTable();
    }

    @FXML
    public void details() {
        System.out.println("Details");
    }

    @FXML
    public void register() {
        System.out.println("Register");
    }

    @FXML
    public void backwards() {
        System.out.println("Backwards");
    }
    
    private void fillTable(){
        System.out.println("Filling table: " + tableClients);
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clients = clienteDAO.getAllClients();

        clientsList.clear();
        clientsList.addAll(clients);


        columnName.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableClients.setItems(clientsList);
    }
}
