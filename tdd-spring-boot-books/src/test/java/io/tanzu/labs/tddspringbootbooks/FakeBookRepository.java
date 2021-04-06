package io.tanzu.labs.tddspringbootbooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBookRepository implements BookRepository {

    private Map<Integer, Book> map;

    public FakeBookRepository() {
        map = new HashMap<>();
        map.put(1, new Book(1, "Book1"));
        map.put(2, new Book(2, "Book2"));
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Book getBook(int id) {
        return map.get(id);
    }
}
