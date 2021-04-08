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
    public Book add(NewBook newBook) {
        Book book = new Book(bookMap.size() + 1, newBook);
        bookMap.put(book.getId(), book);
        return book;
    }

    @Override
    public Book update(int id, UpdateBook updateBook) {
        if (bookMap.containsKey(id) == false) {
            throw new RuntimeException("No book to update for id " + id);
        }
        Book updatedBook = new Book(id, updateBook.getName());
        bookMap.put(id, updatedBook);
        return updatedBook;
    }

    @Override
    public void delete(int id) {
        if (bookMap.containsKey(id) == false) {
            throw new RuntimeException("No book to delete for id " + id);
        }
        bookMap.remove(id);
    }
}
