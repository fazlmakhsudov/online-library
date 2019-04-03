package net.codejava.javaee.service.impl;


import net.codejava.javaee.entity.User;
import net.codejava.javaee.repository.UserRepository;
import net.codejava.javaee.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int add(User item) throws SQLException {
        return userRepository.create(item);
    }

    public boolean add(String email, String password) throws SQLException {
        return userRepository.create(email, password);
    }

    public User find(int id) throws SQLException {
        return userRepository.read(id);
    }

    public User find(String email, String password) throws SQLException {
        return userRepository.read(email, password);
    }

    public boolean save(User item) throws SQLException {
        return userRepository.update(item);
    }

    public boolean remove(int id) throws SQLException {
        return userRepository.delete(id);
    }

    public List<User> findAll() throws SQLException {
        return userRepository.getAll();
    }
}
