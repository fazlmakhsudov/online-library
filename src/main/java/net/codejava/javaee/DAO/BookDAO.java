package net.codejava.javaee.DAO;

import net.codejava.javaee.entity.Book;
import net.codejava.javaee.util.DBInfo;
import net.codejava.javaee.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 *
 * @author www.codejava.net
 */
public class BookDAO {
    private DBUtil DBUtil;

    public BookDAO() {
        this.DBUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
    }

    public boolean insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO book (title, author, price) VALUES (?, ?, ?)";
        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowInserted;
    }

    public List<Book> listAllUsers() throws SQLException {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";

        DBUtil.connect();

        Statement statement = DBUtil.getJdbcConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            Book book = new Book(id, title, author, price);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        DBUtil.disconnect();

        return listBook;
    }

    public boolean deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";

        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, book.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";
        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowUpdated;
    }

    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";

        DBUtil.connect();

        PreparedStatement statement = DBUtil.getJdbcConnection().prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            book = new Book(id, title, author, price);
        }

        resultSet.close();
        statement.close();

        return book;
    }
}
