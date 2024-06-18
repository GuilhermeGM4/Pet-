package org.controller.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.model.Cliente;

import java.util.Objects;

public class VisualizarClienteController extends Application {
    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIniciaEdicao;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnVisualizar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<?> tablePet;

    @FXML
    private Button btnAdicionarPet;

    @FXML
    private TextField txtfNome;

    @FXML
    private TextField txtfCpf;

    @FXML
    private TextField txtfIdade;

    Cliente client;

    @Override
    public void start(Stage stage) throws Exception {
        //TODO: corrigir erro que "visualizar_cliente.fxml" nao esta sendo achado
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("visualizar_cliente.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Gerenciar Clientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        //TODO: setar todos os dados dos campos
        txtfNome.setText(client.getNome());
        txtfCpf.setText(client.getCpf());
        txtfIdade.setText(Integer.toString(client.getIdade()));
    }

    @FXML
    void addPet(ActionEvent event) {

    }

    @FXML
    void backwards(ActionEvent event) {

    }

    @FXML
    void handleEditionButton(ActionEvent event) {
        if(btnIniciaEdicao.getText().equals("Iniciar Edição")) {
            btnIniciaEdicao.setText("Cancelar Edição");
            btnEditar.setDisable(false);
            btnVisualizar.setDisable(false);
        } else {
            //TODO: voltar os campos para o estado inicial utilizando o obj client
            btnIniciaEdicao.setText("Iniciar Edição");
            btnEditar.setDisable(true);
            btnVisualizar.setDisable(true);
        }
    }

    @FXML
    void removePet(ActionEvent event) {

    }

    @FXML
    void viewPet(ActionEvent event) {

    }
//TODO: mockar um cliente para fazer testes
    public void setClient(Cliente c){
        client = c;
    }

}
