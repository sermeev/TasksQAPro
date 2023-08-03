package site.components;

import static org.assertj.core.api.Assertions.assertThat;

import common.annotations.Component;
import common.exceptions.AnnotationEmptyException;
import common.webobjects.AComponent;
import common.webobjects.APage;
import org.openqa.selenium.WebDriver;

@Component("//a[@href='$url']")
public class Label extends AComponent<Label> {
    public Label(WebDriver driver) throws AnnotationEmptyException {
        super(driver);
    }

    public Label click() throws AnnotationEmptyException {
        getComponentEntity().click();
        return this;
    }

    public void checkResult(){
        assertThat(driver.getCurrentUrl().equals(APage.getUrl()))
                .as("The transition to the main page is not successful")
                .isTrue();
    }
}
