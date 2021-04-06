package io.tanzu.labs.tddspringbootbooks;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getBook(int id);

}
