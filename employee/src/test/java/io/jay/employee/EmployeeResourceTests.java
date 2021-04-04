package io.jay.employee;

import io.jay.employee.domain.Employee;
import io.jay.employee.helper.JsonUtil;
import io.jay.employee.helper.NameValue;
import io.jay.employee.helper.NameValueList;
import io.jay.employee.rest.EmployeeResource;
import io.jay.employee.service.EmployeeLogic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeResourceTests {

    private static final String API_PATH = "/api/v1/employees";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeLogic logic;

    @Test
    public void testFindAllWhenEmpty() throws Exception {
        String json = mvc.perform(MockMvcRequestBuilders.get(API_PATH))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Employee> all = JsonUtil.fromJsonList(json, Employee.class);
        assertEquals(all.isEmpty(), true);
    }

    @Test
    @Transactional
    @Rollback
    public void testAddSingleEmployee() throws Exception {

        Employee newEmployee = new Employee();

        String createdId = mvc.perform(MockMvcRequestBuilders.post(API_PATH)
                    .content(JsonUtil.toJson(newEmployee))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(newEmployee.getId(), createdId);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindAllWhenNotEmpty() throws Exception {

        logic.addEmployee(new Employee());
        logic.addEmployee(new Employee());

        String json = mvc.perform(MockMvcRequestBuilders.get(API_PATH))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Employee> all = JsonUtil.fromJsonList(json, Employee.class);
        assertEquals(all.isEmpty(), false);
        assertEquals(all.size(), 2);
    }

    @Test
    @Transactional
    @Rollback
    public void testFindSingle() throws Exception {
        Employee employee = new Employee();
        employee.setFirstName("first");
        employee.setLastName("last");
        employee.setEmail("firstlast@email.com");

        logic.addEmployee(employee);

        String json = mvc.perform(MockMvcRequestBuilders.get(API_PATH + "/" + employee.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Employee e = JsonUtil.fromJson(json, Employee.class);
        assertEquals(employee.getId(), e.getId());
        assertEquals(employee.getFirstName(), e.getFirstName());
        assertEquals(employee.getLastName(), e.getLastName());
        assertEquals(employee.getEmail(), e.getEmail());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateSingle() throws Exception {
        Employee employee = new Employee();
        logic.addEmployee(employee);

        NameValueList nvl = new NameValueList();
        nvl.add(new NameValue("firstName", "first"));
        nvl.add(new NameValue("lastName", "last"));
        nvl.add(new NameValue("email", "firstlast@domain.com"));

        mvc.perform(MockMvcRequestBuilders.put(API_PATH + "/" + employee.getId()).content(JsonUtil.toJson(nvl)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Employee after = logic.retrieve(employee.getId());

        assertEquals(employee.getId(), after.getId());
        assertEquals(after.getFirstName(), "first");
        assertEquals(after.getLastName(), "last");
        assertEquals(after.getEmail(), "firstlast@domain.com");
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteSingle() throws Exception {
        Employee employee = new Employee();
        logic.addEmployee(employee);

        mvc.perform(MockMvcRequestBuilders.delete(API_PATH + "/" + employee.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThrows(RuntimeException.class, () -> logic.retrieve(employee.getId()));
    }
}
