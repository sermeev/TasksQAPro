package common.factory.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeWebDriver implements IDriver {
    @Override
    public WebDriver newDriver() {
        //ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
