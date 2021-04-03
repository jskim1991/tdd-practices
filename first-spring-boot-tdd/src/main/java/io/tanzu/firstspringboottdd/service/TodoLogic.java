package io.tanzu.firstspringboottdd.service;

import io.tanzu.firstspringboottdd.domain.Todo;
import io.tanzu.firstspringboottdd.store.TodoStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional()
public class TodoLogic {

    @Autowired
    private TodoStore store;

    public List<Todo> findAll() {
        return store.retrieveAll();
    }

    public Todo create(Todo todo) {
        return store.insert(todo);
    }
}
