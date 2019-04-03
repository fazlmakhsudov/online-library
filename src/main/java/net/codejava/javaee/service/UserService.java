package net.codejava.javaee.service;

import net.codejava.javaee.entity.User;

import java.sql.SQLException;

public interface UserService extends BaseService<User> {
    public User find(String email, String password) throws SQLException;

    public boolean add(String email, String password) throws SQLException;
}

