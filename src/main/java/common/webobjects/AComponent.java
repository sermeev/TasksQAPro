package common.webobjects;

import common.annotations.Component;
import common.exceptions.AnnotationEmptyException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public abstract class AComponent<T> extends AWebObject<T> {

    String definedPath;

    public AComponent(WebDriver driver) throws AnnotationEmptyException {
        super(driver);
        definedPath = getComponent();
        waiters.waitForElementVisible(getElement(definedPath));
    }

    public AComponent(WebDriver driver, String postfix) throws AnnotationEmptyException{
        super(driver);
        definedPath = getComponent()+postfix;
        waiters.waitForElementVisible(getElement(definedPath));

    }

    private String getComponent() throws AnnotationEmptyException {
        Class<? extends AComponent> classAComponent = this.getClass();
        if (classAComponent.isAnnotationPresent(Component.class)) {
            Component component = classAComponent.getAnnotation(Component.class);
            return component.value();
        }

        throw new AnnotationEmptyException(Component.class.getName());
    }
    protected WebElement getComponentEntity(){
        return getElement(definedPath);
    }
}
