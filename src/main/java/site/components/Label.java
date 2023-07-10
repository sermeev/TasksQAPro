package site.components;

import common.annotations.Component;
import common.exceptions.AnnotationEmptyException;
import common.webobjects.AComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

@Component("//a[@href='$url']")
public class Label extends AComponent<Label> {
    public Label(WebDriver driver) throws AnnotationEmptyException {
        super(driver);
    }

    public void click() throws AnnotationEmptyException {
        getComponentEntity().click();
    }
}
