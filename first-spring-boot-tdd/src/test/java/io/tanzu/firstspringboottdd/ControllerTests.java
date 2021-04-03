package io.tanzu.firstspringboottdd;

import io.tanzu.firstspringboottdd.domain.Todo;
import io.tanzu.firstspringboottdd.service.TodoLogic;
import io.tanzu.firstspringboottdd.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TodoLogic logic;

	/**
	 * given:
	 * when: retrieve all todos
	 * then: result should be empty
	 */
	@Test
	public void findAllWhenEmpty() throws Exception {
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
				.andExpect(status().isOk())
				.andReturn();
		String json = mvcResult.getResponse().getContentAsString();
		List<Todo> list = JsonUtil.fromJsonList(json, Todo.class);

		assertEquals(list.isEmpty(), true);
	}

	/**
	 * given:
	 * when: add a new todo
	 * then: returns new todo
	 */
	@Test
	@Transactional
	@Rollback
	public void addNewTodo() throws Exception {
		Todo newTodo = new Todo();
		newTodo.setName("title");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/todo").content(JsonUtil.toJson(newTodo)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
		String json = mvcResult.getResponse().getContentAsString();

		Todo todo = JsonUtil.fromJson(json, Todo.class);
		assertEquals(todo.getName(), newTodo.getName());

		List<Todo> all = logic.findAll();
		assertEquals(all.size(), 1);
	}

	/**
	 * given:
	 * when: add a new todo and get all todos
	 * then: result should contain one todo
	 */
	@Test
	@Transactional
	@Rollback
	public void findAllAfterAddingTodo() throws Exception {
		Todo todo = new Todo();
		todo.setName("new todo");
		logic.create(todo);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
				.andExpect(status().isOk())
				.andReturn();
		String json = mvcResult.getResponse().getContentAsString();
		List<Todo> list = JsonUtil.fromJsonList(json, Todo.class);

		assertEquals(list.isEmpty(), false);
	}


}
