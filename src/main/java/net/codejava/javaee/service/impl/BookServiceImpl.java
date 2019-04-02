package net.codejava.javaee.service.impl;


import net.codejava.javaee.entity.Book;
import net.codejava.javaee.repository.EntityRepository;
import net.codejava.javaee.repository.impl.MySQLBookRepositoryImpl;
import net.codejava.javaee.service.EntityService;
import net.codejava.javaee.util.DBInfo;
import net.codejava.javaee.util.DBUtil;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements EntityService<Book> {
    private static EntityService<Book> instance;

    public static EntityService<Book> getInstance() {
        if (instance == null) {
            DBUtil dbUtil = new DBUtil(DBInfo.getJdbcURL(), DBInfo.getJdbcUsername(), DBInfo.getJdbcPassword());
            ;
            EntityRepository<Book> bookRepository = new MySQLBookRepositoryImpl(dbUtil);
            instance = new BookServiceImpl(bookRepository);
        }
        System.out.println(instance + "*****");
        return instance;
    }

    private EntityRepository<Book> bookRepository;

    private BookServiceImpl(EntityRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int add(Book item) throws SQLException {
        return bookRepository.create(item);
    }

    public Book find(int id) throws SQLException {
        return bookRepository.read(id);
    }

    public boolean save(Book item) throws SQLException {
        return bookRepository.update(item);
    }

    public boolean remove(int id) throws SQLException {
        return bookRepository.delete(id);
    }

    public List<Book> findAll() throws SQLException {
        return bookRepository.getAll();
    }
}
