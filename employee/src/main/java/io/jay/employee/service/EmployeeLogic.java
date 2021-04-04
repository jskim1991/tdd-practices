package io.jay.employee.service;

import io.jay.employee.domain.Employee;
import io.jay.employee.helper.NameValueList;
import io.jay.employee.store.EmployeeStore;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeLogic {

    private final EmployeeStore store;

    public EmployeeLogic(EmployeeStore store) {
        this.store = store;
    }

    public List<Employee> retrieveAll() {
        return store.findAll();
    }

    public String addEmployee(Employee employee) {
        return store.create(employee);
    }

    public Employee retrieve(String id) {
        return store.find(id);
    }

    public void update(String id, NameValueList nameValueList) {
        Employee employee = store.find(id);
        employee.setValues(nameValueList);
        store.update(employee);
    }

    public void delete(String id) {
        store.delete(id);
    }
}
