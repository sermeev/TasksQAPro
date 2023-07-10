package main;

import static org.assertj.core.api.Assertions.assertThat;

import common.annotations.Driver;
import common.enums.TypeSection;
import common.exceptions.AnnotationEmptyException;
import common.extensions.UIExtensions;
import common.webobjects.APage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import site.components.Label;
import site.pages.MainPage;
import java.io.IOException;

@ExtendWith(UIExtensions.class)
public class TestHomeWorkFirst {

    @Driver
    public WebDriver driver;

    private final String nameCourse = "Цифровизация и трансформация бизнеса";

    @Test
    public void testFilterByNameInSectionPopular() throws AnnotationEmptyException, IOException {
        WebElement element = new MainPage(driver)
                .open()
                .getElementCourse(TypeSection.POPULARS,nameCourse);
        assertThat(element!=null)
                .as(String.format("Course not found '%1$s' in section '%2$s'",nameCourse,TypeSection.POPULARS.getTitle()))
                .isTrue();
    }
    @Test
    public void testFilterCourseDateStartIsBefore() throws AnnotationEmptyException, IOException {
        String nameFilteredCourse  = new MainPage(driver)
                .open()
                .getCourseByDateStart(TypeSection.POPULARS,true);
        assertThat(nameCourse.equals(nameFilteredCourse))
                .as(String.format("Course '%1$s' in section '%2$s' not start before all",nameCourse,TypeSection.POPULARS.getTitle()))
                .isTrue();
    }
    @Test
    public void testFilterCourseCostIsMin() throws AnnotationEmptyException, IOException {
        String nameFilteredCourse  = new MainPage(driver)
                .open()
                .getCostCourseByPrice(TypeSection.POPULARS,false);
        assertThat(nameCourse.equals(nameFilteredCourse))
                .as(String.format("Cost course '%1$s' in section '%2$s' not is minimal",nameCourse,TypeSection.POPULARS.getTitle()))
                .isTrue();
    }
    @Test
    public void testClickOnLabelCourse() throws AnnotationEmptyException {
        new MainPage(driver).open();
        new Label(driver).click();
        assertThat(driver.getCurrentUrl().equals(APage.getUrl()))
                .as("The transition to the main page is not successful")
                .isTrue();
    }

    @Test
    public void testClickByCoordinatesNotTransfer() throws AnnotationEmptyException {
        new MainPage(driver).open().moveAndClickCursor(0,0);
        assertThat(driver.getCurrentUrl().equals(APage.getUrl()))
                .as("Transfer to another page")
                .isTrue();
    }
}
