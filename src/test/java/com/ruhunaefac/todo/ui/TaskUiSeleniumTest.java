package com.ruhunaefac.todo.ui;

import com.ruhunaefac.todo.TodoApplication;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TodoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskUiSeleniumTest {

    @LocalServerPort int port;
    static WebDriver driver;

    @BeforeAll static void setupClass() { WebDriverManager.chromedriver().setup(); }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @AfterEach void teardown() { if (driver != null) driver.quit(); }

    @Test
    void addTask_showsInTable() {
        String base = "http://localhost:" + port + "/";
        driver.get(base);
        driver.findElement(By.name("title")).sendKeys("Walk the dog");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.get(base);
        assertTrue(driver.getPageSource().contains("Walk the dog"));
    }

    @Test
    void emptyTitle_showsValidationError() {
        String base = "http://localhost:" + port + "/";
        driver.get(base);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertTrue(driver.getPageSource().contains("Title is required"));
    }
}
