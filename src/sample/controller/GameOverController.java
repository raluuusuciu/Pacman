package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.model.User;

import java.net.URL;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    private ServiceUser su;

    @FXML
    public Button viewButton;
    @FXML
    public Button enrollUser;
    @FXML
    public AnchorPane gameOver;
    @FXML
    public Label message;
    @FXML
    public TableColumn<Object, Object> usernameColumn;
    @FXML
    public TableColumn<Object, Object> scoreColumn;
    @FXML
    public TableColumn<Object, Object> dateColumn;
    @FXML
    public TableView<User> tableScore;
    @FXML
    public TextField enrollMe;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        su = new ServiceUser();

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("resultDate"));

        try {
            viewLeaderBoard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewLeaderBoard() throws SQLException {

        tableScore.getItems().clear();

        List<User> userList = su.findAll();
        userList.sort(Comparator.comparing(User::getScore));

        ObservableList<User> userObservableList = FXCollections.observableList(userList);
        tableScore.getItems().addAll(userObservableList);
    }

    public void addUser() throws SQLException {

        Integer score = GameController.score;
        String username = enrollMe.getText();

        su.saveUser(username, score);
        message.setText("You're in the game! Good luck!");

        viewLeaderBoard();
    }
}
