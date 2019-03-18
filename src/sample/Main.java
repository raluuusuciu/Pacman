package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("controller/sample.fxml"));
        primaryStage.setTitle("MY PACMAN, BEST PACMAN");
        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);

        String musicFile = "D:\\Java\\Laborator4Pacman\\src\\sample\\Stuff\\PAC-MAN Namco Sounds - Start Music.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.minutes(1)));
        mediaPlayer.play();

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
