package org.controller.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import org.DAO.Funcionario.FuncionarioDAO;
import org.Utils.ControllerUtil;
import org.controller.Popups.WarningController;
import org.model.Funcionario;

import java.io.IOException;
import java.util.ArrayList;

public class VisualizarFuncionarioController {

    @FXML
    private TableView<Funcionario> tableFuncionarios;

    @FXML
    private TableColumn<Funcionario, String> columnNome;

    @FXML
    private TableColumn<Funcionario, String> columnCpf;

    @FXML
    private TableColumn<Funcionario, String> columnTelefone; // Adicionado

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtfNome;

    @FXML
    private TextField txtfCpf;

    @FXML
    private TextField txtfIdade;

    @FXML
    private TextField txtfTelefone;

    @FXML
    private ChoiceBox<String> choiceSexo;

    @FXML
    private ChoiceBox<String> choiceFuncao;

    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO(); // Inicializa FuncionarioDAO
    private ControllerUtil controllerUtil = new ControllerUtil();
    private Funcionario funcionarioSelecionado;
    private ObservableList<Funcionario> funcionariosList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializa as listas de dias e carga de trabalho
        ArrayList<String> diasTrabalho = new ArrayList<>();
        diasTrabalho.add("TERÇA");

        ArrayList<String> cargaTrabalho = new ArrayList<>();
        cargaTrabalho.add("2 GRANDE");
        cargaTrabalho.add("1 MÉDIO");

        // Cria um novo funcionário e adiciona à lista de funcionários
        Funcionario funcionario = new Funcionario("Otávio", "Masculino", 20, "50345191862", "16982025724", diasTrabalho, cargaTrabalho);
        funcionariosList.add(funcionario);

        // Configura as colunas da tabela
        populateTable();

        // Preenche os campos com os valores do funcionário selecionado
        setDefaultValues();
    }

    private void setDefaultValues(){
        if (funcionariosList.isEmpty()) return;
        Funcionario funcionario = funcionariosList.get(0);
        txtfNome.setText(funcionario.getNome());
        txtfCpf.setText(funcionario.getCpf());
        txtfIdade.setText(Integer.toString(funcionario.getIdade()));
        txtfTelefone.setText(funcionario.getTelefone());
        choiceSexo.setValue(funcionario.getSexo());
    }

    private void populateTable(){
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tableFuncionarios.setItems(funcionariosList);
    }

    @FXML
    void viewFuncionario(ActionEvent event) throws IOException {
        Funcionario funcionarioSelecionado = tableFuncionarios.getSelectionModel().getSelectedItem();
        if (funcionarioSelecionado == null) {
            showPopup("Selecione um funcionário para visualizar.");
            return;
        }

        FXMLLoader loader = controllerUtil.generateLoader("Funcionario", "visualizar_funcionario.fxml");
        controllerUtil.load(loader);
        controllerUtil.changeScene(event, "Visualizar Funcionario");
    }

    @FXML
    void edit(ActionEvent event) {
        if (funcionarioSelecionado == null) {
            showPopup("Selecione um funcionário para editar.");
            return;
        }

        String result = editarFuncionario();
        showPopup(result);
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Funcionario", "gerenciar_funcionarios.fxml");
        controllerUtil.changeScene(loader, event, "Gerenciar Funcionários");
    }

    private String editarFuncionario() {
        // Implementa a lógica de edição do funcionário
        return "Funcionário alterado com sucesso!";
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionarioSelecionado = funcionario;
        setDefaultValues();
    }

    private void showPopup(String message){
        try {
            FXMLLoader loader = controllerUtil.generateLoader("Popups", "warning_window.fxml");
            controllerUtil.load(loader);
            WarningController controller = (WarningController) controllerUtil.getController();
            controller.setText(message);
            controllerUtil.openWindow("Aviso", true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
