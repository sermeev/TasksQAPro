package com.otus.steps;

import com.google.inject.Inject;
import com.otus.bdd.support.GuiceScoped;
import com.otus.site.pages.LessonPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.То;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class LessonPageSteps {
    @Inject
    private GuiceScoped guiceScoped;

    @Inject
    LessonPage lessonPage;

    @И("Получить стоимость курса")
    public void getCostCourse()
    {
        lessonPage.getCostCourse();
    }

    @Тогда("Откроется страница курса")
    public void pageTitleShouldBeSameAs()
    {
        Assertions.assertEquals(lessonPage.getNameCourse(),guiceScoped.nameCourse);
    }

    @То("Стоимость курса равна {int}")
    public void checkCostCourse(int costCourse)
    {
        Assertions.assertEquals(guiceScoped.costCourse, costCourse);
    }

}
