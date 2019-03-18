package sample.repository;

import sample.util.JDBC;
import sample.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserRepository {

    private JDBC jdbc = new JDBC();
    private List<User> userList = new ArrayList<>();

    public List<User> readUsersFromDatabase() throws SQLException {

        userList.clear();

        PreparedStatement preparedStatement = jdbc.createConnection().prepareStatement("SELECT * FROM Users");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            User user = new User(resultSet.getString("username"), resultSet.getInt("score"), resultSet.getDate("resultDate"));
            userList.add(user);
        }

        return userList;
    }

    private boolean findUser(String username) {

        for (User user : userList) {

            if (user.getUsername().equals(username))
                return true;
        }

        return false;
    }

    public void addOrUpdateUser(String username, Integer score) throws SQLException {

        if (!findUser(username)) {

            Calendar calendar = Calendar.getInstance();
            Date date = new Date(calendar.getTime().getTime());

            String sqlQueryInsert = "INSERT INTO Users(username, score, resultDate)" + "VALUES(?, ?, ?)";

            PreparedStatement preparedStatement = jdbc.createConnection().prepareStatement(sqlQueryInsert);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, score);
            preparedStatement.setDate(3, date);

            preparedStatement.executeUpdate();

        } else {
            for (User user : userList) {
                if (user.getUsername().equals(username)) {

                    if (user.getScore() < score) {

                        Calendar calendar = Calendar.getInstance();
                        Date date = new Date(calendar.getTime().getTime());

                        String sqlQueryUpdate = "UPDATE Users SET score = ?, resultDate = ? WHERE username = ?";

                        PreparedStatement preparedStatement = jdbc.createConnection().prepareStatement(sqlQueryUpdate);
                        preparedStatement.setInt(1, score);
                        preparedStatement.setDate(2, date);
                        preparedStatement.setString(3, username);

                        preparedStatement.executeUpdate();
                    }
                }
            }
        }
    }
}
