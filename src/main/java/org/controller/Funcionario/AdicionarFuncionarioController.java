package org.controller.Funcionario;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.DAO.Funcionario.FuncionarioDAO;
import org.UseCases.GerenciarFuncionario.CadastrarFuncionario;
import org.Utils.ControllerUtil;
import org.model.Funcao;
import org.model.Funcionario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AdicionarFuncionarioController extends Application {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<String> choiceFuncao;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtCargaTrabalho1;

    @FXML
    private TextField txtCargaTrabalho2;

    @FXML
    private TextField txtDiaTrabalho1;

    @FXML
    private TextField txtDiaTrabalho2;

    @FXML
    private TextField txtNome;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtTelefone;

    private final ControllerUtil controllerUtil = new ControllerUtil();

    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adicionar_funcionario.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Cadastro de Funcionarios");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {
        initializeChoiceBoxes();
    }

    private void initializeChoiceBoxes() {
        choiceGender.getItems().addAll("Masculino", "Feminino", "Outro");
        choiceFuncao.getItems().addAll("Gerente", "Atendente", "Outra");
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "gerenciar_cliente.fxml");
        controllerUtil.changeScene(loader, event, "Gerenciar Clientes");
    }

    @FXML
    void register() {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        String nome = txtNome.getText();
        String cpf = txtCPF.getText();
        String telefone = txtTelefone.getText();
        String gender = choiceGender.getValue();
        String funcao = choiceFuncao.getValue();
        String idadeStr = txtIdade.getText();
        String cargaTrabalho1 = txtCargaTrabalho1.getText();
        String cargaTrabalho2 = txtCargaTrabalho2.getText();
        String diaTrabalho1 = txtDiaTrabalho1.getText();
        String diaTrabalho2 = txtDiaTrabalho2.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || gender == null || funcao == null ||
                idadeStr.isEmpty() || cargaTrabalho1.isEmpty() || cargaTrabalho2.isEmpty() ||
                diaTrabalho1.isEmpty() || diaTrabalho2.isEmpty()) {
            txtMessage.setText("Preencha todos os campos!");
            return;
        }

        try {
            int idade = Integer.parseInt(idadeStr);
            ArrayList<String> diasTrabalho = new ArrayList<>();
            diasTrabalho.add(diaTrabalho1);
            diasTrabalho.add(diaTrabalho2);

            ArrayList<String> cargasTrabalho = new ArrayList<>();
            cargasTrabalho.add(cargaTrabalho1);
            cargasTrabalho.add(cargaTrabalho2);

            CadastrarFuncionario registration = new CadastrarFuncionario();
            String result = registration.cadastrar(nome, gender, idade, cpf, telefone,
                    Funcao.valueOf(funcao.toUpperCase()), diasTrabalho, cargasTrabalho);

            txtMessage.setText(result);

            // Adiciona o funcionário à lista se o cadastro foi bem-sucedido
            if ("Funcionário cadastrado com sucesso!".equals(result)) {
                Funcionario novoFuncionario = new Funcionario(nome, gender, idade, cpf, telefone, diasTrabalho, cargasTrabalho);
                funcionarioDAO.adicionarFuncionario(novoFuncionario);
            }
        } catch (NumberFormatException e) {
            txtMessage.setText("Idade não é um número válido!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
