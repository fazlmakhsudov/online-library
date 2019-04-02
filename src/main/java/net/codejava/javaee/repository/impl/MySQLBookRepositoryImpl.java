package net.codejava.javaee.repository.impl;


import net.codejava.javaee.entity.Book;
import net.codejava.javaee.repository.EntityRepository;
import net.codejava.javaee.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLBookRepositoryImpl implements EntityRepository<Book> {


    private final DBUtil dbUtil;

    public MySQLBookRepositoryImpl(DBUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public int create(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, price, user_id) VALUES (?, ?, ?, ?)";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getUser_id());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        if (rowInserted) {
            return book.getId();
        }
        return -1;
    }

    @Override
    public Book read(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";
        dbUtil.connect();
        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            int user_id = resultSet.getInt("user_id");

            book = new Book(id, title, author, price, user_id);
        }

        resultSet.close();
        statement.close();

        return book;
    }

    @Override
    public boolean update(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?, user_id = ?";
        sql += " WHERE book_id = ?";
        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getUser_id());

        statement.setInt(5, book.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowUpdated;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";

        dbUtil.connect();

        PreparedStatement statement = dbUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        dbUtil.disconnect();
        return rowDeleted;
    }

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";

        dbUtil.connect();

        Statement statement = dbUtil.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");
            int user_id = resultSet.getInt("user_id");

            Book book = new Book(id, title, author, price, user_id);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        dbUtil.disconnect();

        return listBook;
    }
}
