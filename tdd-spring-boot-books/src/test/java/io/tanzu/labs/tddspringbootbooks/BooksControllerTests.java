package io.tanzu.labs.tddspringbootbooks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BooksControllerTests {

    private MockMvc mockMvc;
    private StubBookRepository stubBookRepository;

    @BeforeEach
    public void setup() {
        stubBookRepository = new StubBookRepository();

        mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(stubBookRepository))
                .build();
    }

    @Test
    public void test_getAll_returnsOk() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void test_getAll_returnsEmptyArray() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
        ;
    }

    @Test
    public void test_getAll_returnsSingleBook() throws Exception {
        stubBookRepository.setData(Collections.singletonList(new Book(1, "Book1")));

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].name", equalTo("Book1")))
        ;
    }

    @Test
    public void test_getBook_returnsEmptyArray() throws Exception {
        mockMvc.perform(get("/books/single/1"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void test_getBook_returnsSingleBook() throws Exception {
        stubBookRepository.setSingleData(new Book(1, "Book1"));

        mockMvc.perform(get("/books/single/1"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")))
        ;
    }

    @Test
    public void test_getBook_returnsCorrectId() throws Exception {

        SpyBookRepository spyBookRepository = new SpyBookRepository();
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(spyBookRepository))
                .build();

        mockMvc.perform(get("/books/single/111"));

        assertThat(spyBookRepository.getId(), equalTo(111));
    }

    @Test
    public void test_getAllFromDB_returnsList() throws Exception {

        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(fakeBookRepository))
                .build();

        mockMvc.perform(get("/books"))
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Book1")))
                .andExpect(jsonPath("$[1].id", equalTo(2)))
                .andExpect(jsonPath("$[1].name", equalTo("Book2")))
        ;
    }

    @Test
    public void test_getBookFromDB_returnsSingleBook() throws Exception {
        FakeBookRepository fakeBookRepository = new FakeBookRepository();
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(new BooksController(fakeBookRepository))
                .build();

        mockMvc.perform(get("/books/single/1"))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Book1")))
        ;
    }
}
