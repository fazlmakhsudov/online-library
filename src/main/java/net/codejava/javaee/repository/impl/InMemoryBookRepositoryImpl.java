package net.codejava.javaee.repository.impl;


import net.codejava.javaee.entity.Book;
import net.codejava.javaee.repository.BaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InMemoryBookRepositoryImpl implements BaseRepository<Book> {


    private final Map<Integer, Book> memory;
    private int index;


    public InMemoryBookRepositoryImpl(final Map<Integer, Book> memory, final int
            startIndex) {
        this.memory = memory;
        this.index = startIndex;
    }

    @Override
    public int create(final Book item) {
        item.setId(index);
        memory.put(index, item);
        return index++;
    }

    @Override
    public Book read(final int id) {
        return memory.getOrDefault(id, null);
    }

    @Override
    public boolean update(final Book item) {
        if (!memory.containsKey(item.getId())) {
            return false;
        }
        memory.put(item.getId(), item);
        return true;
    }

    @Override
    public boolean delete(final int id) {
        if (!memory.containsKey(id)) {
            return false;
        }
        memory.remove(id);
        return true;
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(memory.values());
    }
}
