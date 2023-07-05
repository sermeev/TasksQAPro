package main;

import common.annotations.Driver;
import common.enums.TypeBlockLessons;
import common.exceptions.AnnotationEmptyException;
import common.extensions.UIExtensions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import site.pages.MainPage;
@ExtendWith(UIExtensions.class)
public class TestHomeWorkFirst {

    @Driver
    public WebDriver driver;

    @Test
    public void test() throws AnnotationEmptyException {
        int size = new MainPage(driver)
                .open()
                .getBlockLessons(TypeBlockLessons.POPULARS)
                .getListBlockLesson().size();
    }
}
