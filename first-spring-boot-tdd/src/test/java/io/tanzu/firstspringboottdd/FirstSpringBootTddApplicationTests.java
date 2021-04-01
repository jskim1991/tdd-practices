package io.tanzu.firstspringboottdd;

import io.tanzu.firstspringboottdd.domain.Todo;
import io.tanzu.firstspringboottdd.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
		"logging.level.root=info"
})
@AutoConfigureMockMvc
class FirstSpringBootTddApplicationTests {

	@Autowired
	private MockMvc mockMvc;

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
	public void addNewTodo() throws Exception {
		Todo newTodo = new Todo();
		newTodo.setName("title");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/todo").content(JsonUtil.toJson(newTodo)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
		String json = mvcResult.getResponse().getContentAsString();

		Todo todo = JsonUtil.fromJson(json, Todo.class);
		assertEquals(todo.getName(), newTodo.getName());
	}

//	/**
//	 * given:
//	 * when: add a new todo and get all todos
//	 * then: result should contain one todo
//	 */
//	@Test
//	public void findAllWhenNotEmpty() throws Exception {
//		MvcResult addResult = mockMvc.perform(MockMvcRequestBuilders.post("/todos"))
//		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
//				.andExpect(status().isOk())
//				.andReturn();
//		String json = mvcResult.getResponse().getContentAsString();
//		List<Todo> list = JsonUtil.fromJsonList(json, Todo.class);
//
//		assertEquals(list.isEmpty(), true);
//	}
}
