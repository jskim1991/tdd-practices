package io.jay.employee.store;

import io.jay.employee.domain.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EmployeeJpo {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public EmployeeJpo(Employee employee) {
        BeanUtils.copyProperties(employee, this);
    }

    public Employee toDomain() {
        Employee employee = new Employee();
        BeanUtils.copyProperties(this, employee);
        return employee;
    }
}
