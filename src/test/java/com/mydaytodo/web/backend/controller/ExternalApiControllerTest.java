package com.mydaytodo.web.backend.controller;

import com.mydaytodo.web.backend.models.ChuckNorrisJoke;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class ExternalApiControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value(value = "${local.server.port}")
    private int port;

    private String LOCAL_HOST_URL = "http://localhost:";

    @Test
    void testGetJokes() {
        String url = LOCAL_HOST_URL + port + "/proxy/api/jokes";
        ChuckNorrisJoke joke = testRestTemplate.getForObject(url, ChuckNorrisJoke.class);
        assertThat(joke).isNotNull();
    }
}
