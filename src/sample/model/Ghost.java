package sample.model;

import java.util.Random;

public class Ghost {

    private Integer x;
    private Integer y;

    public Ghost() {

        Random random = new Random();
        this.x = random.nextInt(10);
        this.y = random.nextInt(10);
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

    public Ghost getGhost() {

        return this;
    }
}
