package io.tanzu.firstspringboottdd.controller;

import io.tanzu.firstspringboottdd.domain.Todo;
import io.tanzu.firstspringboottdd.service.TodoLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoLogic logic;

    @GetMapping("/todos")
    public List<Todo> getAllTodo() {
        return logic.findAll();
    }

    @PostMapping("/todo")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Todo addTodo(@RequestBody Todo todo) {
        return logic.create(todo);
    }
}
