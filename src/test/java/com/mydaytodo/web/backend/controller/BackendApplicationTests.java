package com.mydaytodo.web.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private HelloMDTController helloMDTController;

	@Autowired
	private ExternalApiController externalApiController;
	@Autowired
	private TodoController todoController;
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(helloMDTController).isNotNull();
		assertThat(externalApiController).isNotNull();
		assertThat(todoController).isNotNull();
		assertThat(userController).isNotNull();
	}
}
