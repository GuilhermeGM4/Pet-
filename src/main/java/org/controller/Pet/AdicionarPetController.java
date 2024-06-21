package org.controller.Pet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.Utils.ControllerUtil;

import java.io.IOException;

public class AdicionarPetController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancel;

    @FXML
    private ChoiceBox<?> choiceGender;

    @FXML
    private ChoiceBox<?> choicePorte;

    @FXML
    private ChoiceBox<?> choiceRaca;

    @FXML
    private TextField txtIdade;

    @FXML
    private Text txtMessage;

    @FXML
    private TextField txtNome;

    private ControllerUtil controllerUtil = new ControllerUtil();

    @FXML
    void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = controllerUtil.generateLoader("Cliente", "visualizar_cliente.fxml");
        controllerUtil.changeScene(event, "Visualizar Cliente");
    }

    @FXML
    void register(ActionEvent event) {

    }

}
