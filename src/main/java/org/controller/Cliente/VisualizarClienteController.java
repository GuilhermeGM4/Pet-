package org.controller.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.UseCases.GerenciarCliente.EditarCliente;
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
    private ChoiceBox<String> choiceSexo;

    @FXML
    private TableView<?> tablePet;

    @FXML
    private TableColumn<?, ?> columnNome;

    @FXML
    private TableColumn<?, ?> columnRaca;

    @FXML
    private Button btnAdicionarPet;

    @FXML
    private TextField txtfNome;

    @FXML
    private TextField txtfCpf;

    @FXML
    private TextField txtfIdade;

    @FXML
    private TextField txtfTelefone;

    Cliente client = new Cliente("John Doe", "Masculino", 19, "12345678900", "12345678901");

    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("visualizar_cliente.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Gerenciar Clientes");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        choiceSexo.getItems().addAll("Masculino", "Feminino", "Outro");
        setDefaultValues();
    }

    @FXML
    void edit(ActionEvent event) {
        EditarCliente editor = new EditarCliente();
        String result = editor.editar(
                txtfNome.getText(),
                txtfCpf.getText(),
                choiceSexo.getValue(),
                Integer.parseInt(txtfIdade.getText()),
                txtfTelefone.getText()
        );
        //TODO: adicionar um text na janela para mostrar mensagens do sistema e implementar
        if(!result.equals("Cliente alterado com sucesso")){
            System.out.println(result);
        }
        btnIniciaEdicao.setText("Iniciar Edição");
        changeEditableStatus(false);
    }

    @FXML
    void addPet(ActionEvent event) {
        //TODO: implementar
    }

    @FXML
    void backwards(ActionEvent event) {
        //TODO: implementar
    }

    @FXML
    void handleEditionButton(ActionEvent event) {
        if(btnIniciaEdicao.getText().equals("Iniciar Edição")) {
            btnIniciaEdicao.setText("Cancelar Edição");
            changeEditableStatus(true);
        } else {
            btnIniciaEdicao.setText("Iniciar Edição");
            changeEditableStatus(false);
            setDefaultValues();
        }
    }

    private void changeEditableStatus(boolean status){
        btnEditar.setDisable(!status);
        txtfNome.setEditable(status);
        txtfIdade.setEditable(status);
        txtfTelefone.setEditable(status);
        choiceSexo.setDisable(!status);
    }

    private void setDefaultValues(){
        txtfNome.setText(client.getNome());
        txtfCpf.setText(client.getCpf());
        txtfIdade.setText(Integer.toString(client.getIdade()));
        txtfTelefone.setText(client.getTelefone());

        choiceSexo.setValue(client.getSexo());
    }

    @FXML
    void removePet(ActionEvent event) {
        //TODO: implementar
    }

    @FXML
    void viewPet(ActionEvent event) {
        //TODO: implementar
    }

    private void populateTable(){
        //TODO: implementar
    }

    public void setClient(Cliente c){
        client = c;
    }

}
