package sample.model;

public class Block {

    private Integer row;
    private Integer column;
    private Integer isWall;

    public Block(Integer row, Integer column, Integer isWall) {

        this.row = row;
        this.column = column;
        this.isWall = isWall;
    }

    Integer getRow() {
        return row;
    }

    Integer getColumn() {
        return column;
    }

    public Integer getIsWall() {
        return isWall;
    }

    public void setIsWall(Integer isWall) {
        this.isWall = isWall;
    }


}
