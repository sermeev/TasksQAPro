package common.factory;

import common.exceptions.BrowserNotSupportException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class DriverFactory {
    private final static String BROWSER_NAME = System.getProperty("browser", "chrome");

    public EventFiringWebDriver getDriver() throws BrowserNotSupportException {

        switch (BROWSER_NAME) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true); // Для включения просмотра по VNC client через веб-часть.
                capabilities.setCapability("screenResolution", "1920x1080x24"); // Ширина, высота, глубина цвета
                capabilities.setBrowserName("chrome"); //Браузер в котором нужно проверять.
                capabilities.setVersion("115.0"); //Версия браузера.
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                try {
                    return new EventFiringWebDriver(new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub/"), capabilities));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                //return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
            }
            default:
                throw new BrowserNotSupportException(BROWSER_NAME);
        }
    }
}
