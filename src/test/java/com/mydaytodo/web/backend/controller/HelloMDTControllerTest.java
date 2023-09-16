package com.mydaytodo.web.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloMDTControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Value(value="${local.server.port}")
    private int port;

    @Test
    void testSayHello() throws Exception {
        assertThat(testRestTemplate
                .getForObject("http://localhost:"+port+"/api/greet", String.class))
                .contains("HelloWorld");
    }
    @Test
    void testPing() {
        String url = "http://localhost:"+port+"/ping";
        ResponseEntity<Void> entity = testRestTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Void.class);
       assertThat(entity.getStatusCode()).satisfies(httpStatusCode -> httpStatusCode.equals(HttpStatus.OK));
    }
}
