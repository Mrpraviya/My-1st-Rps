package com.ruhunaefac.todo.bdd;

import com.ruhunaefac.todo.TodoApplication;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StepDefs {

    @LocalServerPort int port;
    String listJson;

    @Given("the app is running")
    public void the_app_is_running() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @When("I add a task titled {string}")
    public void i_add_a_task(String title) {
        RestAssured.given().contentType(ContentType.JSON)
                .body("{\"title\":\"" + title + "\"}")
                .when().post("/api/tasks").then().statusCode(201);
        listJson = RestAssured.get("/api/tasks").then().statusCode(200).extract().asString();
    }

    @Then("I should see {string} in the list")
    public void i_should_see_in_the_list(String title) {
        assertThat(listJson, containsString(title));
    }
}

