package ru.qa.pro.tasks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstTaskTest {
    private static WebDriver driver;
    @BeforeAll
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @AfterAll
    public static void tearDown(){
        if(driver != null)
            driver.quit();
    }
    @Test
    public void test(){
        driver.get("https://otus.ru");
        driver.findElement(By.cssSelector(".div")).click();
    }
}
