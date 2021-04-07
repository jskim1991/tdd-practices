package io.tanzu.labs.tddspringbootbooks;

import java.util.ArrayList;
import java.util.List;

public class FakeBookRepository implements BookRepository {

    private List<Book> bookList;

    public FakeBookRepository() {
        bookList = new ArrayList<>();
    }

    @Override
    public List<Book> getAll() {
        return bookList;
    }

    @Override
    public Book getBook(int id) {
        for (Book book : bookList) {
            if (book.getId() == id) {
                return book;
            }
        }

        throw new RuntimeException("No such book for id " + id);
    }

    @Override
    public void add(NewBook newBook) {
        Book book = new Book(bookList.size() + 1, newBook);
        bookList.add(book);
    }
}
