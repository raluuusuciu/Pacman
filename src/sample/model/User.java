package sample.model;


import java.sql.Date;

public class User {

    private String username;
    private int score;
    private Date resultDate;

    public User(String username, int score, Date resultDate){

        this.username = username;
        this.score = score;
        this.resultDate = resultDate;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }
}
