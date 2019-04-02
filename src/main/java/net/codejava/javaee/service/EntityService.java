package net.codejava.javaee.service;


import java.sql.SQLException;
import java.util.List;

public interface EntityService<T> {

    int add(T item) throws SQLException;

    T find(int id) throws SQLException;

    boolean save(T item) throws SQLException;

    boolean remove(int id) throws SQLException;

    List<T> findAll() throws SQLException;
}
