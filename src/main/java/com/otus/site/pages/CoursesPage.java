package com.otus.site.pages;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.common.annotations.Path;
import com.otus.common.webobjects.APage;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Path("/catalog/courses")
public class CoursesPage extends APage {


    @Inject
    public CoursesPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private final String coursesInSectionLocator = "//h6/div[contains(text(),'%s')]";
    private final String startTimeCourseLocator = "/ancestor::a//div[contains(text(),'месяц')]";

    public void clickCourse(String nameCourse){
        WebElement elementCourse = getWebElementCourse(nameCourse,coursesInSectionLocator);
        elementCourse.click();
    }

    private WebElement getWebElementCourse(String nameCourse, String path) {
        WebElement elementCourse;
        guiceScoped.nameCourse = nameCourse;
        elementCourse =getElement(String.format(path, nameCourse));
        return elementCourse;
    }
    private LocalDate convertDateStartToLocalDate(String dateStart){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy",new Locale("ru", "RU"));
        date = LocalDate.parse(dateStart, formatter);
        return date;

    }

    public void getStartTimeCourse(String nameCourse) {
        WebElement element = getWebElementCourse(nameCourse,coursesInSectionLocator+startTimeCourseLocator);
        String startTime = element.getText();
        startTime = startTime.substring(0, startTime.indexOf(" · ")).replace(",","");
        LocalDate date = convertDateStartToLocalDate(startTime);
        guiceScoped.startTimeCourse = date;
    }
}
