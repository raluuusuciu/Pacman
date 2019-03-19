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

public class YouWonController implements Initializable {


    private ServiceUser su = new ServiceUser();

    @FXML
    public AnchorPane youWonPane;
    @FXML
    public TableColumn<Object, Object> usernameColumn;
    @FXML
    public TableColumn<Object, Object> scoreColumn;
    @FXML
    public TableColumn<Object, Object> dateColumn;
    @FXML
    public Button viewLeaderBoard;
    @FXML
    public TableView<User> tableScore;
    @FXML
    public Button addUser;
    @FXML
    public TextField enterUsername;
    @FXML
    public Label message;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("resultDate"));

        List<User> userList = null;
        try {
            userList = su.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert userList != null;
        userList.sort(Comparator.comparing(User::getScore));

        ObservableList<User> userObservableList = FXCollections.observableList(userList);
        tableScore.getItems().addAll(userObservableList);
    }

    @FXML
    public void showResults() throws SQLException {

        tableScore.getItems().clear();

        List<User> userList = su.findAll();
        userList.sort(Comparator.comparing(User::getScore));

        ObservableList<User> userObservableList = FXCollections.observableList(userList);
        tableScore.getItems().addAll(userObservableList);
    }

    @FXML
    public void enrollMe() throws SQLException {

        String username = enterUsername.getText();

        su.saveUser(username, 44);
        message.setText("You're in the game! Good luck!");
    }
}
