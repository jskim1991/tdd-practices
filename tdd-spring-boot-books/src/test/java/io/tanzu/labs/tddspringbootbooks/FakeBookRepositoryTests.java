package io.tanzu.labs.tddspringbootbooks;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FakeBookRepositoryTests {

    @Test
    void test_getAllBooks_returnsEmptyList() {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();


        assertThat(fakeBookRepository.getAll().isEmpty(), equalTo(true));
    }

    @Test
    void test_getAllBooks_returnsBooks() {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        fakeBookRepository.add(new NewBook("Book1"));


        List<Book> books = fakeBookRepository.getAll();


        assertThat(books.isEmpty(), equalTo(false));
        assertThat(books.get(0).getId(), equalTo(1));
        assertThat(books.get(0).getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_returnsBook() {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        fakeBookRepository.add(new NewBook("Book1"));


        Book actualBook = fakeBookRepository.getBook(1);


        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_throwsException() {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        Exception thrownException = null;


        try {
            fakeBookRepository.getBook(999);
        } catch (Exception e) {
            thrownException = e;
        }


        assertThat(thrownException.getClass(), equalTo(RuntimeException.class));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }
}
