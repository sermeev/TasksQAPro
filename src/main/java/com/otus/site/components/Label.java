package com.otus.site.components;

import com.otus.common.annotations.Component;
import com.otus.common.exceptions.AnnotationEmptyException;
import com.otus.common.webobjects.AComponent;
import org.openqa.selenium.WebDriver;

@Component("//a[@href='$url']")
public class Label extends AComponent<Label> {
    public Label(WebDriver driver) throws AnnotationEmptyException {
        super(driver);
    }

    public void click() throws AnnotationEmptyException {
        getComponentEntity().click();
    }
}
