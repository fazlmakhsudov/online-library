package net.codejava.javaee.dao;

import net.codejava.javaee.entity.User;
import net.codejava.javaee.util.DBInfo;
import net.codejava.javaee.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DBUtil DBUtil;

    public UserDAO() {
        this.DBUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
    }

    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user (name, password, email) VALUES (?, ?, ?)";
        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowInserted;
    }

    public List<User> listAllUsers() throws SQLException {
        List<User> listUsers = new ArrayList<>();

        String sql = "SELECT * FROM user";

        DBUtil.connect();

        Statement statement = DBUtil.getJdbcConnection().createStatement();
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

        DBUtil.disconnect();

        return listUsers;
    }

    public boolean deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM user where user_id = ?";

        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, user.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET name = ?, password = ?, email = ?";
        sql += " WHERE user_id = ?";
        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setInt(4, user.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowUpdated;
    }

    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";

        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");

            user = new User(id, name, password, email);
        }

        resultSet.close();
        statement.close();

        return user;
    }
}
