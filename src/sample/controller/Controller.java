package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public Button start;
    @FXML
    public AnchorPane pane1;

    @FXML
    public void startGame() throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("input.fxml"));
        Scene sceneInput = new Scene(parent, 600, 300);
        Stage stageInput = new Stage();
        stageInput.setScene(sceneInput);
        stageInput.setTitle("INPUT FIRST");
        stageInput.show();

    }
}
