package sample.view;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import sample.model.Maze;
import sample.Stuff.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.stream.IntStream;


public class View {

    public Rectangle drawWall(Integer row, Integer column, Integer isWall) throws FileNotFoundException {

        Rectangle block = null;

        if (isWall.equals(Constants.WALL)) {

            block = new Rectangle(row * 50, column * 50, 50, 50);
            block.setFill(Color.BLACK);
        }

        if (isWall.equals(Constants.EMPTY)) {

            block = new Rectangle(row * 50, column * 50, 50, 50);
            block.setFill(Color.WHITE);
        }

        if (isWall.equals(Constants.FOOD)) {

            block = new Rectangle(row * 50, column * 50, 50, 50);
            Image blockImage = new Image(new FileInputStream("D:\\AN 2\\SEMESTRUL 1\\Java\\Laborator4Pacman\\src\\sample\\Stuff\\food.jpg"));
            ImagePattern blockImagePattern = new ImagePattern(blockImage);
            block.setFill(blockImagePattern);
        }

        return block;
    }

    public Rectangle drawPacman(Integer x, Integer y) throws FileNotFoundException {

        Rectangle pacman;


        pacman = new Rectangle(x * 50, y * 50, 50, 50);
        Image pacImageOpen = new Image(new FileInputStream("D:\\AN 2\\SEMESTRUL 1\\Java\\Laborator4Pacman\\resources\\pacmanRight.jpg"));
        ImagePattern pacImagePattern = new ImagePattern(pacImageOpen);
        pacman.setFill(pacImagePattern);

        return pacman;

    }

    public Rectangle drawGhost(Integer x, Integer y) throws FileNotFoundException {

        Rectangle ghost = new Rectangle(x * 50, y * 50, 50, 50);
        Image ghostImage = new Image(new FileInputStream("D:\\AN 2\\SEMESTRUL 1\\Java\\Laborator4Pacman\\src\\sample\\Stuff\\ghost3.jpg"));
        ImagePattern ghostImagePattern = new ImagePattern(ghostImage);
        ghost.setFill(ghostImagePattern);

        return ghost;
    }

    public void drawMaze(Maze maze, AnchorPane root) {

        IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                row -> IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                        col -> {
                            try {
                                root.getChildren().add(drawWall(row, col, maze.getBlock(row, col).getIsWall()));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                ));
    }
}