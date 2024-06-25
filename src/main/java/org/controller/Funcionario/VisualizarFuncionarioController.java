package org.controller.Funcionario;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.UseCases.GerenciarCliente.EditarCliente;
import org.UseCases.GerenciarFuncionario.EditarFuncionario;
import org.Utils.ControllerUtil;
import org.controller.Pet.AdicionarPetController;
import org.controller.Pet.VisualizarPetController;
import org.controller.Popups.WarningController;
import org.model.Cliente;
import org.model.Funcionario;
import org.model.Pet;

import java.io.IOException;

public class VisualizarFuncionarioController {

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

        ObservableList<Pet> petList = FXCollections.observableArrayList();

        @FXML
        public void initialize() {
            setDefaultValues();
        }

        @FXML
        void edit(ActionEvent event) {
            EditarFuncionario editor = new EditarFuncionario();
            editor.setFuncionario(funcionario);
            String result = editor.ed(
                    txtfNome.getText(),
                    txtfCpf.getText(),
                    txtfIdade.getText(),
                    choiceSexo.getValue(),
                    txtfTelefone.getText()
            );
            if(!result.equals("Cliente alterado com sucesso")){
                setDefaultValues();
            }
            System.out.println(result);
            btnIniciaEdicao.setText("Iniciar Edição");
            changeEditableStatus(false);
            showPopup(result);
        }

        @FXML
        void addPet(ActionEvent event) throws IOException {
            FXMLLoader loader = controllerUtil.generateLoader("Pet", "adicionar_pet.fxml");
            controllerUtil.load(loader);
            AdicionarPetController controller = (AdicionarPetController) controllerUtil.getController();
            controller.setOwner(client);
            controllerUtil.changeScene(event, "Adicionar Pet");
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
            Pet selectedPet = tablePet.getSelectionModel().getSelectedItem();
            Pet pet = petList.filtered(p -> p.getNome().equals(selectedPet.getNome()))
                    .stream()
                    .findFirst()
                    .orElse(null);
            if(pet == null){
                showPopup("Erro ao adquirir informações do pet.");
                return;
            }
//        client.removerPet(pet);
            petList.remove(pet);

            EditarCliente editor = new EditarCliente();
            editor.setClient(client);
            String result = editor.removePet(pet.getNome());
            petList.remove(pet);

            showPopup(result);

            populateTable();
        }

        @FXML
        void viewPet(ActionEvent event) throws IOException {
            Pet selectedPet = tablePet.getSelectionModel().getSelectedItem();
            Pet pet = petList.filtered(p -> p.getNome().equals(selectedPet.getNome()))
                    .stream()
                    .findFirst()
                    .orElse(null);
            if(pet == null){
                System.out.println("Error getting pet.");
                showPopup("Erro ao adquirir informações do pet.");
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
            petList.clear();
            petList.addAll(client.getPets());
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

}
