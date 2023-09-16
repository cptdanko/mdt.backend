package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.ChuckNorrisJoke;
import com.mydaytodo.web.backend.restclient.JokesClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ExternalApiControllerTest {

    private MockMvc mockMvc;
    @Mock
    private JokesClient jokesClient;
    @InjectMocks
    private ExternalApiController externalApiController;

    @Value(value = "${local.server.port}")
    private int port;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(externalApiController).build();
    }

    @Test
    void testGetJokes() throws Exception {
        when(jokesClient.getJokes()).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        mockMvc.perform(get("/proxy/api/jokes"))
                .andExpect(status().isOk());
    }
}
