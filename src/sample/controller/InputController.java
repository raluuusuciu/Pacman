package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InputController {

    @FXML
    public AnchorPane paneInput;
    @FXML
    public TextField speedG;
    @FXML
    public TextField numberG;
    @FXML
    public Button startGame;
    @FXML
    public ChoiceBox<String> niveauChoiceBox;
    @FXML
    public Label message;

    @FXML
    public void openGame() {

        AnchorPane root = new AnchorPane();
        root.setPrefWidth(500);
        root.setPrefHeight(500);
        Scene scene = new Scene(root, 500, 500);
        Stage startGameWindow = new Stage();
        startGameWindow.setScene(scene);
        startGameWindow.setTitle("PACMAN *CROWD SCREAMING*");
        startGameWindow.getIcons().add(new Image("pacmanRight.jpg"));

        GameController gc = new GameController(startGameWindow, root);

        String nrGhostsString = numberG.getText();
        Integer nrGhosts = Integer.valueOf(nrGhostsString);
        Integer speed;
        String niveau = niveauChoiceBox.getValue();

        if (getSpeed(niveau) == null)
            speed = Integer.valueOf(speedG.getText());

        else
            speed = getSpeed(niveau);

        try {
            gc.initializeGame(nrGhosts, speed);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.UP) {

                gc.movePacmanUp();
                try {
                    gc.redrawMaze();
                    if (gc.service.mazeIsClear())
                        gc.youWon();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (event.getCode() == KeyCode.DOWN) {

                gc.movePacmanDown();
                try {
                    gc.redrawMaze();
                    if (gc.service.mazeIsClear())
                        gc.youWon();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (event.getCode() == KeyCode.LEFT) {

                gc.movePacmanLeft();
                try {
                    gc.redrawMaze();
                    if (gc.service.mazeIsClear())
                        gc.youWon();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (event.getCode() == KeyCode.RIGHT) {

                gc.movePacmanRight();
                try {
                    gc.redrawMaze();
                    if (gc.service.mazeIsClear())
                        gc.youWon();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        startGameWindow.show();
    }

    private Integer getSpeed(String niveau) {

        if (niveau.equals("Easy")) {

            return 700;
        }

        if (niveau.equals("Medium")) {

            return 600;
        }

        if (niveau.equals("Hard")) {

            return 350;
        }

        if (niveau.equals("Expert")) {

            return 250;
        }

        return null;
    }

    @FXML
    public void printMessage() {

        if (niveauChoiceBox.getSelectionModel().getSelectedItem().equals("Easy"))
            message.setText("Seems like you're a beginner!");

        if (niveauChoiceBox.getSelectionModel().getSelectedItem().equals("Medium"))
            message.setText("You learn quickly!");

        if (niveauChoiceBox.getSelectionModel().getSelectedItem().equals("Hard"))
            message.setText("Getting tough!");

        if (niveauChoiceBox.getSelectionModel().getSelectedItem().equals("Expert"))
            message.setText("Welcome, Master!");

        if (niveauChoiceBox.getSelectionModel().getSelectedItem().equals("Custom"))
            message.setText("Please write the speed you want!");
    }
}
