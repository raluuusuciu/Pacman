package sample.model;

public class Pacman {

    private Integer x;
    private Integer y;
    private static Integer score = 0;

    public Pacman() {

        this.x = 5;
        this.y = 8;
    }

    public static Integer getScore() {
        return score;
    }

    public static void setScore(Integer score) {
        Pacman.score = score;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Pacman getPacman(){

        return this;
    }
}

