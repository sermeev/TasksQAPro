package com.otus.site.pages;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.common.annotations.Path;
import com.otus.common.webobjects.APage;
import org.openqa.selenium.NoSuchElementException;

@Path("/lessons")
public class LessonPage  extends APage {
    private boolean isOldLayoutPage;
    private final String typePageSelector = ".webp";
    private final String getNameOldLayoutPageSelector = ".course-header2__title";
    private final String getNameNewLayoutPageLocator = "//a[@href='%s']";

    @Inject
    public LessonPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
        try {
            getElement(typePageSelector);
            isOldLayoutPage = false;
        }catch (NoSuchElementException exception){
            isOldLayoutPage = true;
        }
    }
    private String getNameNewLayoutPageLocator(){
        String url = guiceScoped.driver.getCurrentUrl();
        return  String.format(getNameNewLayoutPageLocator,url.substring(0,url.length()-1));
    };

    public String getNameCourse(){
        return (isOldLayoutPage?getElement(getNameOldLayoutPageSelector).getText():getElement(getNameNewLayoutPageLocator()).getText());//
    }

}
