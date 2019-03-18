package sample.controller;

import sample.model.*;
import sample.Stuff.Constants;
import sample.view.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Service {

    Maze maze = new Maze();
    View view = new View();
    Pacman pacman = new Pacman();
    static List<Ghost> listGhosts = new ArrayList<>();

    private int[][] readMaze() throws IOException {

        Stream<String> stream = Files.lines(Paths.get(Constants.MAZE_FILE));

        int[][] mazeFile;

        mazeFile = stream
                .map(item -> Pattern.compile(",")
                        .splitAsStream(item)
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);

        return mazeFile;
    }

    Maze initializeMaze() throws IOException {

        int[][] file = readMaze();

        IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                row -> IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                        col -> {
                            Block block = new Block(row, col, file[row][col]);
                            maze.addBlock(block);
                        }
                )
        );

        return maze;
    }

    boolean isObstacle(Integer x, Integer y) {

        return maze.getBlock(x, y).getIsWall().equals(Constants.WALL);
    }

    boolean isFood(Integer x, Integer y) {

        return maze.getBlock(x, y).getIsWall().equals(Constants.FOOD);
    }

    private boolean validatePositionGhost(Ghost ghost) {

        if (isObstacle(ghost.getX(), ghost.getY())) {

            Random random = new Random();
            ghost.setX(random.nextInt(10));
            ghost.setY(random.nextInt(10));

            return validatePositionGhost(ghost);
        }

        return true;
    }

    void listOfGhosts(Integer nrGhosts) {

        IntStream.range(0, nrGhosts).forEach(gh -> {

            Ghost ghost = new Ghost();
            validatePositionGhost(ghost);
            listGhosts.add(ghost);
        });
    }

    boolean mazeIsClear() {

        AtomicBoolean isClear = new AtomicBoolean(true);

        IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                row -> IntStream.iterate(0, i -> i + 1).limit(Constants.GRID_SIZE).forEach(
                        col -> {

                            if (maze.getBlock(row, col).getIsWall().equals(Constants.FOOD))
                                isClear.set(false);
                        }
                )
        );

        return isClear.get();
    }
}
