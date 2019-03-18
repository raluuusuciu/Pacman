package sample.controller;

import sample.model.User;
import sample.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

class ServiceUser {

    private UserRepository userRepository = new UserRepository();

    List<User> findAll() throws SQLException {

        return userRepository.readUsersFromDatabase();
    }

    void saveUser(String username, Integer score) throws SQLException {

        userRepository.addOrUpdateUser(username, score);
    }
}
