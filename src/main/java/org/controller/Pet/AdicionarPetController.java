package org.controller.Pet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.UseCases.GerenciarCliente.EditarCliente;
import org.Utils.ControllerUtil;
import org.model.Cliente;

import java.io.IOException;

public class AdicionarPetController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<String> choiceGender;

    @FXML
    private ChoiceBox<String> choicePorte;

    @FXML
    private ChoiceBox<String> choiceRaca;

    @FXML
    private TextField txtIdade;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtNome;

    private final ControllerUtil controllerUtil = new ControllerUtil();
    private Cliente owner = new Cliente("Johny Doe", "Masculino", 26, "12345678900", "12345678910");

    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "visualizar_cliente.fxml");
        controllerUtil.changeScene(event, "Visualizar Cliente");
    }

    @FXML
    void register(ActionEvent event) {
        EditarCliente editor = new EditarCliente();
        editor.setClient(owner);

        String result = editor.addPet(txtNome.getText(),
                txtIdade.getText(),
                choiceRaca.getSelectionModel().getSelectedItem(),
                choicePorte.getSelectionModel().getSelectedItem());

        txtMessage.setText(result);
        return;
    }

    public void setOwner(Cliente owner) {
        this.owner = owner;
    }
}
