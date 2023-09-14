package com.mydaytodo.web.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydaytodo.web.backend.models.User;
import com.mydaytodo.web.backend.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;
    private final User user = new User();

    private final String TEST_USER_ID = "USR_1357";

    JacksonTester<User> userJacksonTester;

    @BeforeEach
    public void populate() {
        user.setName("Bhuman");
        user.setUserId("USR_123");
        user.setActive(true);
        user.setPreferredCurrencyCode("aud");
        user.setEmail("bhuman@mydaytodo.com");
        user.setUsername("cptdanko");
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }
    /**
     * Write unit tests for the 4 CRUD methods
     */

    @Test
    void testCreateUser() throws Exception {
        when(userService.addUser(any())).thenReturn(user);
        mockMvc.perform(post("/api/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJacksonTester.write(user)
                        .getJson()))
                .andExpect(status().isCreated());

    }
    @Test
    void testDeleteUser() throws Exception {
        when(userService.delete(any())).thenReturn(HttpStatus.NO_CONTENT.value());
        mockMvc.perform(delete("/api/user/"+TEST_USER_ID))
                .andExpect(status().isNoContent());
    }
    @Test
    void testUpdateUser() throws Exception{
        when(userService.update(any(), any())).thenReturn(HttpStatus.NO_CONTENT.value());
        mockMvc.perform(put("/api/user/"+TEST_USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJacksonTester.write(user).getJson()))
                .andExpect(status().isNoContent());
    }
    @Test
    void testGetUser() throws Exception {
        when(userService.getUser(any())).thenReturn(user);
        mockMvc.perform(get("/api/user/"+TEST_USER_ID))
                .andExpect(status().isOk());
    }
}
