package main;

import static org.assertj.core.api.Assertions.assertThat;

import common.annotations.Driver;
import common.enums.TypeSection;
import common.exceptions.AnnotationEmptyException;
import common.extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import site.components.Label;
import site.pages.MainPage;
import java.io.IOException;

@ExtendWith(UIExtensions.class)
public class TestHomeWorkFirst {

    @Driver
    public WebDriver driver;

    private final String nameCourse = "DevRel";

    @Test
    public void testFilterByNameInSectionPopular() throws AnnotationEmptyException, IOException {
        new MainPage(driver)
                .open()
                .getElementCourse(TypeSection.POPULARS,nameCourse);
    }
    @Test
    public void testFilterCourseDateStartIsBefore() throws AnnotationEmptyException, IOException {
        new MainPage(driver)
                .open()
                .getCourseByDateStart(TypeSection.POPULARS, nameCourse, true);

    }
    @Test
    public void testFilterCourseCostIsMin() throws AnnotationEmptyException, IOException {
        new MainPage(driver)
                .open()
                .getCostCourseByPrice(TypeSection.POPULARS, nameCourse, false);
    }
    @Test
    public void testClickOnLabelCourse() throws AnnotationEmptyException {
        new MainPage(driver).open();
        new Label(driver).click()
                .checkResult();

    }

    @Test
    public void testClickByCoordinatesNotTransfer() throws AnnotationEmptyException {
        new MainPage(driver)
                .open()
                .moveAndClickCursor(0,0)
                .checkResult();

    }
}
