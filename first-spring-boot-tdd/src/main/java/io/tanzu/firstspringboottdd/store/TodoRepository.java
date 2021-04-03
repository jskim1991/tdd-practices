package io.tanzu.firstspringboottdd.store;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoJpo, String> {
}
