package org.controller.Cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.UseCases.GerenciarCliente.CadastrarCliente;

import java.util.Objects;

public class AdicionarClienteController extends Application {
    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtNome;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtTelefone;
    @Override
    public void start(Stage stage) throws Exception {
        final Pane graph = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adicionar_cliente.fxml")));
        final Scene scene = new Scene(graph, 800, 600);
        stage.setTitle("Cadastro de Cliente");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize(){
        choiceGender.getItems().addAll("Masculino", "Feminino", "Outro");
    }

    @FXML
    void cancel(ActionEvent event) {
        //TODO: implementar de forma que volte para a tela de listagem de clientes
        System.out.println("Cancel");
    }

    @FXML
    void register() {
        CadastrarCliente registration = new CadastrarCliente();

        int convertedAge;
        try{
            convertedAge = Integer.parseInt(txtIdade.getText());
        }catch(NumberFormatException e){
            txtMessage.setText("Idade não é um número!");
            return;
        }

        String result = registration.cadastrar(txtNome.getText(), choiceGender.getValue(),
                convertedAge, txtCPF.getText(), txtTelefone.getText());
        txtMessage.setText(result);
    }
}
