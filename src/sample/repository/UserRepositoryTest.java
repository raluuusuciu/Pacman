package sample.repository;

import org.junit.jupiter.api.Test;
import sample.model.User;

import java.sql.SQLException;
import java.util.List;


public class UserRepositoryTest {

    @Test
    public void readUsersFromDatabase() throws SQLException, ClassNotFoundException {

        UserRepository userRepository = new UserRepository();

        List<User> test = userRepository.readUsersFromDatabase();

        System.out.println(test);

    }

}