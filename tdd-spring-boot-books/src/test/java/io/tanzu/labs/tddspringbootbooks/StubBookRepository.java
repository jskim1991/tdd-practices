package io.tanzu.labs.tddspringbootbooks;

import java.util.ArrayList;
import java.util.List;

public class StubBookRepository implements BookRepository {

    private List<Book> data;
    private Book singleData;

    public StubBookRepository() {
        data = new ArrayList<>();
    }

    @Override
    public List<Book> getAll() {
        return data;
    }

    @Override
    public Book getBook(int id) {
        return singleData;
    }

    @Override
    public void add(NewBook newBook) {

    }

    @Override
    public void update(int id, UpdateBook updateBook) {

    }


    public void setData(List<Book> data) {
        this.data = data;
    }

    public void setSingleData(Book singleData) {
        this.singleData = singleData;
    }

    public void updateSingleData(Book updateData) {
        this.singleData = updateData;
    }
}
