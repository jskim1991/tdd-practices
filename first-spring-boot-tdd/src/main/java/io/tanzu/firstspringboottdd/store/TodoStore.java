package io.tanzu.firstspringboottdd.store;

import io.tanzu.firstspringboottdd.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TodoStore {

    @Autowired
    private TodoRepository repository;

    public List<Todo> retrieveAll() {
        List<TodoJpo> jpos = repository.findAll();
        return jpos.stream().map(TodoJpo::toDomain).collect(Collectors.toList());

    }

    public Todo insert(Todo todo) {
        TodoJpo saved = repository.save(new TodoJpo(todo));
        return TodoJpo.toDomain(saved);
    }
}
