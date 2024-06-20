package org.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerUtil {
    public void changeScene(FXMLLoader loader, ActionEvent event, String windowTitle) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(windowTitle);
        stage.setScene(scene);
    }

    public void closeWindow(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    //APPLICATION_MODAL define que apenas a nova tela será interagivel no app inteiro enquanto estiver aberta, portanto
    //apenas deixe isAppModel sendo true em avisos e/ou telas de confirmacao.
    public void openWindow(FXMLLoader loader, String title, boolean isAppModal) throws IOException {
        Pane graph = loader.load();
        Scene scene = new Scene(graph);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);

        if(isAppModal) stage.initModality(Modality.APPLICATION_MODAL);

        stage.showAndWait();
    }
}
