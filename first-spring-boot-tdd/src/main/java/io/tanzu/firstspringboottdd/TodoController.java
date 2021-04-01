package io.tanzu.firstspringboottdd;

import io.tanzu.firstspringboottdd.domain.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {
    @GetMapping("/todos")
    public List<Todo> getAllTodo() {
        return new ArrayList<>();
    }

    @PostMapping("/todo")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo) {
        return todo;
    }
}
