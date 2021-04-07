package io.tanzu.labs.tddspringbootbooks;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return repository.getBook(id);
    }

    @PutMapping("/1")
    public void updateBook() {

    }
}
