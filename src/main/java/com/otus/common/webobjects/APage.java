package com.otus.common.webobjects;

import static org.assertj.core.api.Assertions.assertThat;

import com.otus.common.annotations.Path;
import com.otus.common.exceptions.AnnotationEmptyException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.otus.bdd.support.GuiceScoped;


public abstract class APage<T> extends AWebObject {
    protected String url;

    private final static String BASE_URL = System.getProperty("base.url");

    @FindBy(tagName = "h1")
    private WebElement headerTag;

    public T pageHeaderTagShouldBeVisible() {
        assertThat(waiters.waitForElementVisible(headerTag))
                .as("Header should be visible")
                .isTrue();
        return (T) this;
    }

    public static  String getBaseUrl(){
        return BASE_URL+(BASE_URL.endsWith(" /")?"":"/");
    }

    public APage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private String getPath() throws AnnotationEmptyException {
        Class<? extends APage> classAPage = this.getClass();
        if (classAPage.isAnnotationPresent(Path.class)) {
            Path path = classAPage.getAnnotation(Path.class);
            return path.value();
        }

        throw new AnnotationEmptyException(Path.class.getName());
    }

    public T open() throws AnnotationEmptyException {
        String path = getPath();

        if(BASE_URL.endsWith("/") && path.startsWith("/"))
            path = path.substring(1);
        url = BASE_URL +  (!BASE_URL.endsWith("/") && (!path.startsWith("/"))? "/" : "") + path;

        driver.get(url);

        return (T) this;
    }
}
