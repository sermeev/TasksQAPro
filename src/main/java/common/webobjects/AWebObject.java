package common.webobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import common.waiters.Waiters;
import java.util.List;

public abstract class AWebObject<T> {
    protected WebDriver driver;
    protected Actions actions;
    protected Waiters waiters;

    public AWebObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);

        PageFactory.initElements(driver, this);
    }

    protected WebElement getElement(By by){
        return driver.findElement(by);
    }

    protected WebElement getElement(String definedPath){
        return  getElement(byAnalyzer(definedPath));
    }

    protected List<WebElement> getElements(By by){
        return driver.findElements(by);
    }

    protected List<WebElement> getElements(String definedPath){
        return getElements(byAnalyzer(definedPath));
    }

    protected WebElement getChildElement(WebElement parent,String definedPath){
        return  parent.findElement(byAnalyzer(definedPath));
    }

    protected List<WebElement> getChildElements(WebElement parent,String definedPath){
        return parent.findElements(byAnalyzer(definedPath));
    }

    private By byAnalyzer(String definedPath){
        return (definedPath.startsWith("/")?By.xpath(definedPath):By.className(definedPath));
    }
}
