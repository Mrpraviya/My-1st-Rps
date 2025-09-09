package com.ruhunaefac.todo.bdd;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import com.ruhunaefac.todo.TodoApplication;

@CucumberContextConfiguration
@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {
    // This class tells Cucumber how to bootstrap Spring Boot
}

