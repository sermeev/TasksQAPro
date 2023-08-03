package com.otus.site.components;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.common.annotations.Component;
import com.otus.common.exceptions.AnnotationEmptyException;
import com.otus.common.webobjects.AComponent;
import org.openqa.selenium.WebDriver;

@Component("//a[@href='$url']")

public class Label extends AComponent<Label> {
    @Inject
    public Label(GuiceScoped scoped) throws AnnotationEmptyException {
        super(scoped);
    }

    public void click() throws AnnotationEmptyException {
        getComponentEntity().click();
    }
}
