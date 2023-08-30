package com.otus.steps;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.site.pages.LessonPage;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class LessonPageSteps {
    @Inject
    private GuiceScoped guiceScoped;

    @Inject
    LessonPage lessonPage;

    @Тогда("Откроется страница курса")
    public void pageTitleShouldBeSameAs()
    {
        Assertions.assertEquals(lessonPage.getNameCourse(),guiceScoped.nameCourse);
    }
}
