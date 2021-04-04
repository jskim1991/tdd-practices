package io.jay.employee.rest;

import io.jay.employee.domain.Employee;
import io.jay.employee.helper.NameValueList;
import io.jay.employee.service.EmployeeLogic;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeResource {

    private final EmployeeLogic logic;

    public EmployeeResource(EmployeeLogic logic) {
        this.logic = logic;
    }

    @GetMapping("")
    public List<Employee> findAll() {
        return logic.retrieveAll();
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String addEmployee(@RequestBody Employee employee) {
        return logic.addEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee findEmployee(@PathVariable(value = "id") String id) {
        return logic.retrieve(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable(value = "id") String id, @RequestBody NameValueList nameValueList) {
        logic.update(id, nameValueList);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") String id) {
        logic.delete(id);
    }
}
