package net.codejava.javaee.repository;


import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {
    /**
     *
     * Creates product in respective repository.
     *
     * @param item entity to create
     * @return generated product id or -1 if product wasn't created
     */
    int create(T item) throws SQLException;

    T read(int id) throws SQLException;

    /**
     * Saves product updates.
     *
     * @param item T to update
     * @return true if saved successfully, false otherwise
     */
    boolean update(T item) throws SQLException;

    /**
     * Deletes product by id.
     *
     * @param id product id
     *
     * @return true if the product was deleted, false otherwise
     */
    boolean delete(int id) throws SQLException;

    List<T> getAll() throws SQLException;
}
