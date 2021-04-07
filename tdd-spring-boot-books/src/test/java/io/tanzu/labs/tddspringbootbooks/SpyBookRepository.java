package io.tanzu.labs.tddspringbootbooks;

import java.util.List;

public class SpyBookRepository implements BookRepository {

    private int id;

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        this.id = id;
        return null;
    }

    @Override
    public void add(NewBook newBook) {

    }

    @Override
    public void update(int id, UpdateBook updateBook) {

    }

    public int getId() {
        return id;
    }
}
