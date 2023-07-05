package common.waiters;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiters {
    private WebDriverWait webDriverWait = null;

    public Waiters(WebDriver driver) {
        webDriverWait = new WebDriverWait(driver, 10);
    }

    public boolean waitForConditions(ExpectedCondition condition) {
        try {
            webDriverWait.until(condition);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    public boolean waitForElementVisible(WebElement element) {
        return waitForConditions(ExpectedConditions.visibilityOf(element));
    }
}
