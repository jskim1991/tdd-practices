package io.tanzu.firstspringboottdd;

import io.tanzu.firstspringboottdd.domain.Todo;
import io.tanzu.firstspringboottdd.service.TodoLogic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LogicTests {

    @Autowired
    private TodoLogic logic;

    /**
     * given:
     * when: retrieve all todos
     * then: result should be empty
     */
    @Test
    public void testFindAllLogicWhenEmpty() {
        List<Todo> todos = logic.findAll();
        assertEquals(todos.isEmpty(), true);
    }

    /**
     * given:
     * when: add a todo
     * then: result should not be empty
     */
    @Test
    @Transactional
    @Rollback
    public void testFindAllAfterAddingTodo() {
        Todo todo = new Todo();
        todo.setName("first todo");
        logic.create(todo);
        List<Todo> todos = logic.findAll();
        assertEquals(todos.isEmpty(), false);
    }

}
