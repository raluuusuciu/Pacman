package sample.model;

public class Maze {


    private Block[][] maze;

    public Maze() {

        maze = new Block[20][20];
    }

    public void addBlock(Block block) {

        maze[block.getRow()][block.getColumn()] = block;
    }

    public Block getBlock(Integer row, Integer column) {

        return maze[row][column];
    }
}

