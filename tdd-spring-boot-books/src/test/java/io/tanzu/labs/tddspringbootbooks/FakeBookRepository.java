package io.tanzu.labs.tddspringbootbooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBookRepository implements BookRepository {

    private Map<Integer, Book> bookMap;

    public FakeBookRepository() {
        bookMap = new HashMap<>();
    }

    @Override
    public List<Book> getAll() {
        return new ArrayList<>(bookMap.values());
    }

    @Override
    public Book getBook(int id) {
        if (bookMap.containsKey(id)) {
            return bookMap.get(id);
        }

        throw new RuntimeException("No such book for id " + id);
    }

    @Override
    public void add(NewBook newBook) {
        Book book = new Book(bookMap.size() + 1, newBook);
        bookMap.put(book.getId(), book);
    }

    @Override
    public void update(int id, UpdateBook updateBook) {
        Book currentBook = getBook(id);
        Book updatedBook = currentBook.update(updateBook);
        bookMap.put(id, updatedBook);
    }
}
