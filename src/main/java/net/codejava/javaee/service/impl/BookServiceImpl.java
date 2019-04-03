package net.codejava.javaee.service.impl;


import net.codejava.javaee.entity.Book;
import net.codejava.javaee.repository.BookRepository;
import net.codejava.javaee.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
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
