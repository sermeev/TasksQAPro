package com.otus.common.factory;

import com.otus.common.exceptions.BrowserNotSupportException;
import com.otus.common.factory.impl.ChromeWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverFactory {
    private final static String BROWSER_NAME = System.getProperty("browser", "chrome");

    public EventFiringWebDriver getDriver() throws BrowserNotSupportException {
        switch (BROWSER_NAME) {
            case "chrome": {
                return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
            }
            default:
                throw new BrowserNotSupportException(BROWSER_NAME);
        }
    }
}
