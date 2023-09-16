package com.mydaytodo.web.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydaytodo.web.backend.dao.TodoDAOImpl;
import com.mydaytodo.web.backend.models.Todo;
import com.mydaytodo.web.backend.service.TodoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// web tutorial: https://thepracticaldeveloper.com/guide-spring-boot-controller-tests/
// @WebMvcTest(TodoController.class)
@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TodoServiceImpl todoService;

    private TodoDAOImpl todoDAO;
    @InjectMocks
    private TodoController todoController;

    /* @MockBean
    private TodoDAOImpl todoDAO; */

    private final Todo testTodo = new Todo();
    private final String TEST_TODO_ID = "TD_1357";
    JacksonTester<Todo> todoJson;

    @BeforeEach
    public void populate() {
        testTodo.setUserId("USR_123");
        testTodo.setText("New Todo");
        testTodo.setDate(new Date());
        testTodo.setId("TD_1357");
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(todoController)
                .build();

    }

    /**
     * @throws Exception
     */
    @Test
    void testCreate() throws Exception {
        when(todoService.addTodo(any())).thenReturn(testTodo);
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/todo/add").contentType(MediaType.APPLICATION_JSON).content(
                        todoJson.write(testTodo).getJson()
                )).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }
    @Test
    void testGetTodo() throws Exception {
        when(todoService.getTodo(any())).thenReturn(testTodo);
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/todo/"+TEST_TODO_ID).contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
    @Test
    void testUpdateTodo() throws Exception {
        when(todoService.updateTodo(any(), any())).thenReturn(true);

        MockHttpServletResponse response = mockMvc.perform(
                put("/api/todo/"+TEST_TODO_ID).contentType(MediaType.APPLICATION_JSON)
                        .content(todoJson.write(testTodo).getJson()))
                .andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());

    }
    @Test
    void testDeleteTodo() throws Exception {
        when(todoService.deleteTodo(TEST_TODO_ID)).thenReturn(HttpStatus.NO_CONTENT.value());
        mockMvc.perform(delete("/api/todo/TD_1357"))
                .andExpect(status().isNoContent());
    }
}
