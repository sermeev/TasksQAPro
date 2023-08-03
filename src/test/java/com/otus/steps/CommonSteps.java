package com.otus.steps;

import com.google.inject.Inject;
import com.otus.common.exceptions.BrowserNotSupportException;
import com.otus.common.factory.DriverFactory;
import io.cucumber.java.ru.Пусть;

public class CommonSteps {

    @Inject
    private DriverFactory driverFactory;

    @Пусть("Открыт браузер {string}")
    public void openBrowser(String browserName) throws BrowserNotSupportException {
        driverFactory.getDriver(browserName);
    }

}