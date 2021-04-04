package io.jay.employee.store;

import io.jay.employee.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeStore {

    private final EmployeeRepository repository;

    public EmployeeStore(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        List<EmployeeJpo> all = repository.findAll();
        return all.stream().map(EmployeeJpo::toDomain).collect(Collectors.toList());
    }

    public String create(Employee employee) {
        EmployeeJpo saved = repository.save(new EmployeeJpo(employee));
        return saved.getId();
    }

    public Employee find(String id) {
        EmployeeJpo employeeJpo = repository.findById(id).orElseThrow(() -> new RuntimeException("No such employee " + id));
        return employeeJpo.toDomain();
    }

    public void update(Employee employee) {
        repository.save(new EmployeeJpo(employee));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
