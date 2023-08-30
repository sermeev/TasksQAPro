package com.otus.steps;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.common.exceptions.BrowserNotSupportException;
import com.otus.common.factory.DriverFactory;
import io.cucumber.java.ru.Пусть;
import java.util.concurrent.TimeUnit;

public class CommonSteps {

    @Inject
    private DriverFactory driverFactory;

    @Inject
    private GuiceScoped guiceScoped;

    @Пусть("Открыт браузер {string}")
    public void openBrowser(String browserName) throws BrowserNotSupportException {
        guiceScoped.driver = driverFactory.getDriver(browserName);
        guiceScoped.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}