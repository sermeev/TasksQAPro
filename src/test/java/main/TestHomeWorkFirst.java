package main;

import static org.assertj.core.api.Assertions.assertThat;

import com.otus.common.annotations.Driver;
import com.otus.common.enums.TypeSection;
import com.otus.common.exceptions.AnnotationEmptyException;
import com.otus.common.extensions.UIExtensions;
import com.otus.common.webobjects.APage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.otus.site.components.Label;
import com.otus.site.pages.MainPage;
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
        assertThat(driver.getCurrentUrl().equals(APage.getBaseUrl()))
                .as("The transition to the main page is not successful")
                .isTrue();
    }

    @Test
    public void testClickByCoordinatesNotTransfer() throws AnnotationEmptyException {
        new MainPage(driver).open().moveAndClickCursor(0,0);
        assertThat(driver.getCurrentUrl().equals(APage.getBaseUrl()))
                .as("Transfer to another page")
                .isTrue();
    }
}
