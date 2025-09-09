package com.ruhunaefac.todo.api;

import com.ruhunaefac.todo.TodoApplication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskApiTest {

    @LocalServerPort int port;

    @BeforeEach
    void setup() { RestAssured.baseURI = "http://localhost"; RestAssured.port = port; }

    @Test
    void createTask_returns201_andPayload() {
        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"title\":\"Study QA\"}")
                .when().post("/api/tasks")
                .then().statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("Study QA"))
                .body("completed", equalTo(false));
    }

    @Test
    void createTask_invalidTitle_returns400() {
        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"title\":\"\"}")
                .when().post("/api/tasks")
                .then().statusCode(400);
    }
}
