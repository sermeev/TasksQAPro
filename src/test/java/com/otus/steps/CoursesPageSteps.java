package com.otus.steps;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.common.exceptions.AnnotationEmptyException;
import com.otus.site.pages.CoursesPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.То;
import org.junit.jupiter.api.Assertions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CoursesPageSteps {
    @Inject
    private CoursesPage coursesPage;

    @Inject
    private GuiceScoped guiceScoped;

    private LocalDate convertDateStartToLocalDate(String dateStart){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy",new Locale("ru", "RU"));
        date = LocalDate.parse(dateStart, formatter);
        return date;

    }

    @Пусть("Открыта страница с курсами")
    public void openCoursesPage() throws AnnotationEmptyException {
        coursesPage.open();
    }
    @Если("Выбрать курс с названием {string}")
    public void selectCoursesByName(String nameCourse){
        coursesPage.clickCourse(nameCourse);
    }
    @Если("Найти дату старта курса {string}")
    public void searchStartTime(String nameCourse){
        coursesPage.getStartTimeCourse(nameCourse);
    }

    @То("Старт курса {string}")
    public void assertStartTime(String startTimeCourse){
        boolean ass=guiceScoped.startTimeCourse.isEqual(convertDateStartToLocalDate(startTimeCourse));
        Assertions.assertTrue(guiceScoped.startTimeCourse.isEqual(convertDateStartToLocalDate(startTimeCourse)));
    }

}
