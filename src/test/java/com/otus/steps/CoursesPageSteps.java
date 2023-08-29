package com.otus.steps;

import com.google.inject.Inject;
import com.otus.common.exceptions.AnnotationEmptyException;
import com.otus.site.pages.CoursesPage;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;

public class CoursesPageSteps {
    @Inject
    private CoursesPage coursesPage;
    @Пусть("Открыта страница с курсами")
    public void openCoursesPage() throws AnnotationEmptyException {
        coursesPage.open();
    }
    @Если("Выбрать курс с названием {string}")
    public void selectCoursesByName(String nameCourse){
        coursesPage.getElementCourse(nameCourse);
    }
}
