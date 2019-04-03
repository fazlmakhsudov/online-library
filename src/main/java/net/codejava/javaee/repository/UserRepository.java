package net.codejava.javaee.repository;

import net.codejava.javaee.entity.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository<User>{
    public boolean create(String email, String password) throws SQLException;
    public User read(String email, String password) throws SQLException;
}
