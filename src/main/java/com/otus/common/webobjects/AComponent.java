package com.otus.common.webobjects;

import com.otus.common.annotations.Component;
import com.otus.common.exceptions.AnnotationEmptyException;
import org.openqa.selenium.WebElement;
import com.otus.common.support.GuiceScoped;


public abstract class AComponent<T> extends AWebObject<T> {
    private final static String BASE_URL = System.getProperty("base.url");


    {
        waiters.waitForElementVisible(getElement(getComponent()));
    }

    public AComponent(GuiceScoped guiceScoped) throws AnnotationEmptyException {
        super(guiceScoped);

    }

    private String getComponent() throws AnnotationEmptyException {
        Class<? extends AComponent> classAComponent = this.getClass();
        if (classAComponent.isAnnotationPresent(Component.class)) {
            Component component = classAComponent.getAnnotation(Component.class);
            String value = component.value();
            return value.replace("$url",APage.getBaseUrl());
        }

        throw new AnnotationEmptyException(Component.class.getName());
    }
    protected WebElement getComponentEntity() throws AnnotationEmptyException {
        return getElement(getComponent());
    }
}
