package com.otus.bdd.support;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;

@ScenarioScoped
public class GuiceScoped {
    public WebDriver driver = null;

    public String nameCourse;

    public LocalDate startTimeCourse;
}
