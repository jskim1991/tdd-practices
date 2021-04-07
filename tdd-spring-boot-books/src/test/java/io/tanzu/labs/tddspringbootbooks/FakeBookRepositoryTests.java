package io.tanzu.labs.tddspringbootbooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakeBookRepositoryTests {

    private FakeBookRepository fakeBookRepository;

    @BeforeEach
    void setup() {
        fakeBookRepository = new FakeBookRepository();
    }

    @Test
    void test_getAllBooks_returnsEmptyList() {
        assertThat(fakeBookRepository.getAll().isEmpty(), equalTo(true));
    }

    @Test
    void test_getAllBooks_returnsBooks() {
        fakeBookRepository.add(new NewBook("Book1"));


        List<Book> books = fakeBookRepository.getAll();


        assertThat(books.isEmpty(), equalTo(false));
        assertThat(books.get(0).getId(), equalTo(1));
        assertThat(books.get(0).getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_returnsBook() {
        fakeBookRepository.add(new NewBook("Book1"));


        Book actualBook = fakeBookRepository.getBook(1);


        assertThat(actualBook.getId(), equalTo(1));
        assertThat(actualBook.getName(), equalTo("Book1"));
    }

    @Test
    void test_getBook_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class, () ->
                fakeBookRepository.getBook(999));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }

    @Test
    void test_getBookAfterUpdate_returnsUpdatedBook() {
        fakeBookRepository.add(new NewBook("Book1"));
        fakeBookRepository.update(1, new UpdateBook("Updated Book1"));


        Book book = fakeBookRepository.getBook(1);


        assertThat(book.getId(), equalTo(1));
        assertThat(book.getName(), equalTo("Updated Book1"));
    }

    @Test
    void test_updateBookWhichDoesNotExist_throwsException() {
        RuntimeException thrownException = assertThrows(RuntimeException.class, () ->
                fakeBookRepository.update(999, new UpdateBook("Updated Book1")));
        assertThat(thrownException.getMessage(), equalTo("No such book for id 999"));
    }
}
