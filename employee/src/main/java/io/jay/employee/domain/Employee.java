package io.jay.employee.domain;

import io.jay.employee.helper.NameValue;
import io.jay.employee.helper.NameValueList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Employee {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    public Employee() {
        this.id = UUID.randomUUID().toString();
    }

    public void setValues(NameValueList nameValueList) {
        for (NameValue nameValue : nameValueList.getNameValues()) {
            String name = nameValue.getName();
            String value = nameValue.getValue();

            switch (name) {
                case "firstName":
                    this.firstName = value;
                    break;
                case "lastName":
                    this.lastName = value;
                    break;
                case "email":
                    this.email = value;
                    break;
                default:
                    throw new IllegalArgumentException("unknown field " + name);
            }
        }
    }
}
