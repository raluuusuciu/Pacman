package sample.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.model.Ghost;
import sample.model.Pacman;
import sample.Stuff.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

public class GameController {

    @FXML
    public AnchorPane root;
    private Stage stage;
    Service service = new Service();
    private Timeline timeline;
    static Integer score = 0;

    GameController(Stage stage, AnchorPane root) {

        this.stage = stage;
        this.root = root;
    }

    void initializeGame(Integer nrGhosts, Integer speed) throws IOException {

        service.view.drawMaze(service.initializeMaze(), root);
        root.getChildren().add(service.view.drawPacman(service.pacman.getX(), service.pacman.getY()));
        Service.listGhosts.forEach(gh -> {
            try {
                root.getChildren().add(service.view.drawGhost(gh.getX(), gh.getY()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });
        service.listOfGhosts(nrGhosts);

        movingGhost(speed);
    }

    void movePacmanUp() {
        if (service.isObstacle(service.pacman.getPacman().getX(), service.pacman.getPacman().getY() - 1))
            return;

        service.pacman.setX(service.pacman.getX());
        service.pacman.setY(service.pacman.getY() - 1);

        if(service.isFood(service.pacman.getX(), service.pacman.getY())){

            Pacman.setScore(Pacman.getScore() + 1);
            service.maze.getBlock(service.pacman.getX(), service.pacman.getY()).setIsWall(Constants.EMPTY);
        }
    }

    void movePacmanDown() {
        if (service.isObstacle(service.pacman.getPacman().getX(), service.pacman.getPacman().getY() + 1))
            return;

        service.pacman.setX(service.pacman.getX());
        service.pacman.setY(service.pacman.getY() + 1);

        if(service.isFood(service.pacman.getX(), service.pacman.getY())){

            Pacman.setScore(Pacman.getScore() + 1);
            service.maze.getBlock(service.pacman.getX(), service.pacman.getY()).setIsWall(Constants.EMPTY);
        }
    }

    void movePacmanLeft() {
        if (service.isObstacle(service.pacman.getPacman().getX() - 1, service.pacman.getPacman().getY()))
            return;

        service.pacman.setX(service.pacman.getX() - 1);
        service.pacman.setY(service.pacman.getY());

        if(service.isFood(service.pacman.getX(), service.pacman.getY())){

            Pacman.setScore(Pacman.getScore() + 1);
            service.maze.getBlock(service.pacman.getX(), service.pacman.getY()).setIsWall(Constants.EMPTY);
        }
    }

    void movePacmanRight() {
        if (service.isObstacle(service.pacman.getPacman().getX() + 1, service.pacman.getPacman().getY()))
            return;

        service.pacman.setX(service.pacman.getX() + 1);
        service.pacman.setY(service.pacman.getY());

        if(service.isFood(service.pacman.getX(), service.pacman.getY())){

            Pacman.setScore(Pacman.getScore() + 1);
            service.maze.getBlock(service.pacman.getX(), service.pacman.getY()).setIsWall(Constants.EMPTY);
        }
    }

    private void moveGhostUp(Ghost ghost) {

        Service.listGhosts.forEach(gh -> {

            if (service.isObstacle(ghost.getGhost().getX(), ghost.getGhost().getY() - 1))
                return;

            ghost.setX(ghost.getX());
            ghost.setY(ghost.getY() - 1);

            try {
                redrawMaze();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void moveGhostDown(Ghost ghost) {
        Service.listGhosts.forEach(gh -> {

            if (service.isObstacle(ghost.getGhost().getX(), ghost.getGhost().getY() + 1))
                return;

            ghost.setX(ghost.getX());
            ghost.setY(ghost.getY() + 1);

            try {
                redrawMaze();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void moveGhostLeft(Ghost ghost) {
        Service.listGhosts.forEach(gh -> {

            if (service.isObstacle(ghost.getGhost().getX() - 1, ghost.getGhost().getY()))
                return;

            ghost.setX(ghost.getX() - 1);
            ghost.setY(ghost.getY());

            try {
                redrawMaze();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void moveGhostRight(Ghost ghost) {

        Service.listGhosts.forEach(gh -> {

            if (service.isObstacle(ghost.getGhost().getX() + 1, ghost.getGhost().getY()))
                return;

            ghost.setX(ghost.getX() + 1);
            ghost.setY(ghost.getY());

            try {
                redrawMaze();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void move(Ghost ghost) {

        Random random = new Random();

        int rand;

        //if pacman is on the right of the ghost
        if (service.pacman.getX() > ghost.getX()) {

            //if pacman is below the ghost
            if (service.pacman.getY() > ghost.getY()) {

                rand = random.nextInt(2);

                if (rand == 0) {
                    moveGhostDown(ghost);
                } else {
                    moveGhostRight(ghost);
                }
            }

            //if pacman is above the ghost
            if (service.pacman.getY() <= ghost.getY()) {

                rand = random.nextInt(2);

                if (rand == 0) {
                    moveGhostUp(ghost);
                } else {
                    moveGhostRight(ghost);
                }
            }

            //if pacman is on the same column as the ghost
            if (service.pacman.getY().equals(ghost.getY())) {

                rand = random.nextInt(3);

                if (rand == 0) {
                    moveGhostUp(ghost);
                } else if (rand == 1) {
                    moveGhostRight(ghost);
                } else {
                    moveGhostLeft(ghost);
                }
            }

        } else {

            //if pacman is on the left of the ghost
            if (service.pacman.getX() <= ghost.getX()) {

                //daca pacman e mai jos decat fantoma
                if (service.pacman.getY() >= ghost.getY()) {

                    rand = random.nextInt(2);

                    if (rand == 0) {
                        moveGhostDown(ghost);
                    } else {
                        moveGhostLeft(ghost);
                    }
                }

                //if pacman is above the ghost
                if (service.pacman.getY() < ghost.getY()) {

                    rand = random.nextInt(2);

                    if (rand == 0) {
                        moveGhostUp(ghost);
                    } else {
                        moveGhostLeft(ghost);
                    }
                }

                //if pacman is on the same column as the ghost
                if (service.pacman.getY().equals(ghost.getY())) {

                    rand = random.nextInt(3);

                    if (rand == 0) {
                        moveGhostUp(ghost);
                    } else if (rand == 1) {
                        moveGhostRight(ghost);
                    }
                    {
                        moveGhostLeft(ghost);
                    }
                }
            }

            //if pacman and the ghost are on the same tile
            if (service.pacman.getX().equals(ghost.getX()) && service.pacman.getY().equals(ghost.getY())) {

                try {
                    gameOver();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    redrawMaze();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void movingGhost(Integer speed) {

        timeline = new Timeline(new KeyFrame(Duration.millis(speed), event -> Service.listGhosts.forEach(this::move)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    void redrawMaze() throws FileNotFoundException {

        root.getChildren().clear();

        IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                row -> IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                        col -> {
                            try {
                                root.getChildren().add(service.view.drawWall(row, col, service.maze.getBlock(row, col).getIsWall()));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                )
        );

        root.getChildren().add(service.view.drawPacman(service.pacman.getX(), service.pacman.getY()));

        Service.listGhosts.forEach(gh -> {

            try {
                root.getChildren().add(service.view.drawGhost(gh.getX(), gh.getY()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void gameOver() throws IOException {

        score = Pacman.getScore();

        timeline.stop();

        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("gameOver.fxml"));
        Stage gameOverWindow = new Stage();
        gameOverWindow.setTitle("HAHAHA, YOU LOSE");
        Scene scene = new Scene(parent, 634.0, 398.0);
        gameOverWindow.setScene(scene);
        gameOverWindow.getIcons().add(new Image("pacmanRight.jpg"));

        String musicFile = "D:\\AN 2\\SEMESTRUL 1\\Java\\Laborator4Pacman\\src\\sample\\Stuff\\Pacman DeathGame Over Noise (HD).mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.minutes(1)));
        mediaPlayer.play();

        gameOverWindow.show();
    }

    void youWon() throws IOException {

        timeline.stop();

        stage.close();
        Parent parent = FXMLLoader.load(getClass().getResource("youWon.fxml"));
        Stage youWonWindow = new Stage();
        youWonWindow.setTitle("GG, WP");
        Scene scene = new Scene(parent, 600.0, 454.0);
        youWonWindow.setScene(scene);
        youWonWindow.getIcons().add(new Image("pacmanRight.jpg"));

        youWonWindow.show();
    }
}
