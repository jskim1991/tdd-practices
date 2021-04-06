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

    public void setData(List<Book> data) {
        this.data = data;
    }

    public void setSingleData(Book singleData) {
        this.singleData = singleData;
    }
}
