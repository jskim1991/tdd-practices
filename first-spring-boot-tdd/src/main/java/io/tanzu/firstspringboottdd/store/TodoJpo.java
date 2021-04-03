package io.tanzu.firstspringboottdd.store;

import io.tanzu.firstspringboottdd.domain.Todo;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TodoJpo {

    @Id
    private String name;

    public TodoJpo(Todo todo) {
        BeanUtils.copyProperties(todo, this);
    }

    public TodoJpo() {

    }

    public static Todo toDomain(TodoJpo todoJpo) {
        Todo todo = new Todo();
        BeanUtils.copyProperties(todoJpo, todo);
        return todo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
