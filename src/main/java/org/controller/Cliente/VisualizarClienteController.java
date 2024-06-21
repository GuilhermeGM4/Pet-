package org.controller.Cliente;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.UseCases.GerenciarCliente.EditarCliente;
import org.Utils.ControllerUtil;
import org.controller.Pet.VisualizarPetController;
import org.model.Cliente;
import org.model.Pet;
import org.model.Porte;
import org.model.Raca;

import java.io.IOException;
import java.util.Objects;

public class VisualizarClienteController extends Application { //TODO: remover extends e start() para nao deixar a janela inicializavel
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
    private TableView<Pet> tablePet;

    @FXML
    private TableColumn<String, Pet> columnNome;

    @FXML
    private TableColumn<String, Pet> columnRaca;

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

    ControllerUtil controllerUtil = new ControllerUtil();

    Cliente client = new Cliente("John Doe", "Masculino", 19, "12345678900", "12345678901");
    ObservableList<Pet> petList = FXCollections.observableArrayList();

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
        //TODO: retirar mock e pegar pets do client
        Pet pet = new Pet("Rex", 3, Raca.GOLDEN_RETRIEVER, Porte.GRANDE);
        choiceSexo.getItems().addAll("Masculino", "Feminino", "Outro");
        client.cadastrarPet(pet);
        petList.add(pet);
        setDefaultValues();
    }

    @FXML
    void edit(ActionEvent event) {
        EditarCliente editor = new EditarCliente();
        editor.setClient(client);
        String result = editor.editar(
                txtfNome.getText(),
                txtfCpf.getText(),
                txtfIdade.getText(),
                choiceSexo.getValue(),
                txtfTelefone.getText()
        );
        //TODO: adicionar um text na janela para mostrar mensagens do sistema e implementar
        if(!result.equals("Cliente alterado com sucesso")){
            setDefaultValues();
        }
        System.out.println(result);
        btnIniciaEdicao.setText("Iniciar Edição");
        changeEditableStatus(false);
    }

    @FXML
    void addPet(ActionEvent event) {
        //TODO: implementar
    }

    @FXML
    void backwards(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "gerenciar_clientes.fxml");
        controllerUtil.changeScene(loader, event, "Gerenciar Clientes");
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
        txtfCpf.setEditable(status);
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

        populateTable();
    }

    @FXML
    void removePet(ActionEvent event) {
        //TODO: implementar
    }

    @FXML
    void viewPet(ActionEvent event) throws IOException {
        //TODO: Corrigir bugs
        Pet selectedPet = tablePet.getSelectionModel().getSelectedItem();
        Pet pet = petList.filtered(p -> p.getNome().equals(selectedPet.getNome()))
                .stream()
                .findFirst()
                .orElse(null);
        if(pet == null){
            System.out.println("Error getting pet.");
            return;
        }

        FXMLLoader loader = controllerUtil.generateLoader("Pet", "visualizar_pet.fxml");
        controllerUtil.load(loader);
        VisualizarPetController petController = (VisualizarPetController) controllerUtil.getController();
        petController.setPetAndOwner(pet, client);
        controllerUtil.changeScene(event, "Visualizar Pet");
    }

    private void populateTable(){
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnRaca.setCellValueFactory(new PropertyValueFactory<>("raca"));
        tablePet.setItems(petList);
    }

    public void setClient(Cliente client){
        this.client = client;
        setDefaultValues();
    }

}
