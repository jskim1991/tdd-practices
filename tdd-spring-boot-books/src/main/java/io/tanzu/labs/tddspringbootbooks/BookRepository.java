package io.tanzu.labs.tddspringbootbooks;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getBook(int id);

    void add(NewBook newBook);

    void update(int id, UpdateBook updateBook);
}
