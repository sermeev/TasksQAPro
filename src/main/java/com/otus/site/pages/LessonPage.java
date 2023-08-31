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
    private final String nameOldLayoutPageSelector = ".course-header2__title";
    private final String nameNewLayoutPageLocator = "//a[@href='%s']";
    private final String buttonFullCostLocator = "//div[text()='Полная']";
    private final String costNewLayoutLocator = "/ancestor::div[2]/following-sibling::div";
    private final String costOldLayoutLocator ="//div[contains(text(),  'Стоимость обучения')]/following-sibling::div/nobr";

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
        return  String.format(nameNewLayoutPageLocator,url.substring(0,url.length()-1));
    };

    public String getNameCourse(){
        return (isOldLayoutPage?getElement(nameOldLayoutPageSelector).getText():getElement(getNameNewLayoutPageLocator()).getText());//
    }

    public void getCostCourse() {
        if(isOldLayoutPage) {
            guiceScoped.costCourse = Integer.parseInt(getElement(costOldLayoutLocator).getText().replaceAll("[^0-9]", ""));
        } else {
            getElement(buttonFullCostLocator).click();
            guiceScoped.costCourse = Integer.parseInt(getElement(buttonFullCostLocator+costNewLayoutLocator).getText().replaceAll("[^0-9]", ""));
        }
    }
}
