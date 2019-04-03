package net.codejava.javaee.repository.impl;


import net.codejava.javaee.entity.User;
import net.codejava.javaee.repository.UserRepository;
import net.codejava.javaee.util.DBUtil;
import net.codejava.javaee.web.filter.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserRepositoryImpl implements UserRepository {


    private final DBUtil dbUtil;

    public MySQLUserRepositoryImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    public boolean create(String email, String password) throws SQLException {
        String sql = "INSERT INTO user (name, password, email, role) VALUES (?, ?, ?, ?)";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, email.replaceFirst("@.+", ""));
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setString(4, Role.CLIENT.getName());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowInserted;
    }

    @Override
    public int create(User user) throws SQLException {
        String sql = "INSERT INTO user (name, password, email, role) VALUES (?, ?, ?, ?)";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, Role.CLIENT.getName());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        if (rowInserted) {
            return user.getId();
        }
        return -1;
    }

    @Override
    public User read(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            Role role = Role.getRole(resultSet.getString("role"));
            user = new User(id, name, password, email, role);
        }

        resultSet.close();
        statement.close();

        return user;
    }

    public User read(String email, String password) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? and password = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            int id = Integer.parseInt(resultSet.getString("user_id"));
            Role role = Role.getRole(resultSet.getString("role"));
            user = new User(id, name, password, email, role);
        }

        resultSet.close();
        statement.close();
        System.out.println(user);
        return user;
    }

    @Override
    public boolean update(User user) throws SQLException {
        String sql = "UPDATE user SET name = ?, password = ?, email = ?";
        sql += " WHERE user_id = ?";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setInt(4, user.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM user where user_id = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowDeleted;
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> listUsers = new ArrayList<>();

        String sql = "SELECT * FROM user";

        dbUtil.connect();

        Statement statement = dbUtil.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");

            User user = new User(id, name, password, email);
            listUsers.add(user);
        }

        resultSet.close();
        statement.close();

        dbUtil.disconnect();

        return listUsers;
    }
}
