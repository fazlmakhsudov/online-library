package net.codejava.javaee.service.impl;


import net.codejava.javaee.entity.User;
import net.codejava.javaee.repository.EntityRepository;
import net.codejava.javaee.repository.impl.MySQLUserRepositoryImpl;
import net.codejava.javaee.service.EntityService;
import net.codejava.javaee.util.DBInfo;
import net.codejava.javaee.util.DBUtil;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements EntityService<User> {
    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
            EntityRepository<User> userRepository = new MySQLUserRepositoryImpl(dbUtil);
            instance = new UserServiceImpl(userRepository);
        }
        System.out.println(instance + "*****");
        return instance;
    }

    private EntityRepository<User> userRepository;

    private UserServiceImpl(EntityRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public int add(User item) throws SQLException {
        return userRepository.create(item);
    }

    public boolean add(String email, String password) throws SQLException {
        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
        return new MySQLUserRepositoryImpl(dbUtil).create(email, password);
    }

    public User find(int id) throws SQLException {
        return userRepository.read(id);
    }

    public User find(String email, String password) throws SQLException {
        DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
        return new MySQLUserRepositoryImpl(dbUtil).read(email, password);
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
