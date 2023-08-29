package com.otus.site.pages;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.common.annotations.Path;
import com.otus.common.webobjects.APage;
import org.openqa.selenium.WebElement;


@Path("/catalog/courses")
public class CoursesPage extends APage {


    @Inject
    public CoursesPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private final String coursesInSectionLocator = "//h6/div[contains(text(),'%s')]";


    public void getElementCourse(String nameCourse){
        WebElement elementCourse =getElement(String.format(coursesInSectionLocator,nameCourse));
        if(elementCourse!=null) elementCourse.click();
    }

}
