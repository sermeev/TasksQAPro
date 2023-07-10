package common.webobjects;

import common.annotations.Component;
import common.exceptions.AnnotationEmptyException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public abstract class AComponent<T> extends AWebObject<T> {
    private final static String BASE_URL = System.getProperty("base.url");


    {
        waiters.waitForElementVisible(getElement(getComponent()));
    }

    public AComponent(WebDriver driver) throws AnnotationEmptyException {
        super(driver);

    }

    private String getComponent() throws AnnotationEmptyException {
        Class<? extends AComponent> classAComponent = this.getClass();
        if (classAComponent.isAnnotationPresent(Component.class)) {
            Component component = classAComponent.getAnnotation(Component.class);
            String value = component.value();
            return value.replace("$url",APage.getUrl());
        }

        throw new AnnotationEmptyException(Component.class.getName());
    }
    protected WebElement getComponentEntity() throws AnnotationEmptyException {
        return getElement(getComponent());
    }
}
