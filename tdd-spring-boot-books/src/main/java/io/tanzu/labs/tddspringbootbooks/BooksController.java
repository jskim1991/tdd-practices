package io.tanzu.labs.tddspringbootbooks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BookRepository repository;

    public BooksController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Book> getAllBooks() {
        return repository.getAll();
    }

    @GetMapping("/single/{id}")
    public Book getBook(@PathVariable int id) {
        return repository.getBook(id);
    }
}
