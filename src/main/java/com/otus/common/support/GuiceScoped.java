package com.otus.common.support;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
    public WebDriver driver = null;

}
