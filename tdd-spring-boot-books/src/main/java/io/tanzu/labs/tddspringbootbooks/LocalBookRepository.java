package io.tanzu.labs.tddspringbootbooks;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalBookRepository implements BookRepository {

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getBook(int id) {
        return null;
    }
}
