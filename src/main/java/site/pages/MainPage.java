package site.pages;

import static org.assertj.core.api.Assertions.assertThat;

import common.enums.TypeBlockLessons;
import common.exceptions.AnnotationEmptyException;
import common.webobjects.APage;
import org.openqa.selenium.WebDriver;
import common.annotations.Path;
import site.components.BlockLessonsComponent;

@Path("/")
public class MainPage extends APage<MainPage> {
    public MainPage(WebDriver driver) {
        super(driver);
    }
    public BlockLessonsComponent getBlockLessons(TypeBlockLessons typeBlockLessons) throws AnnotationEmptyException {
        return new BlockLessonsComponent(driver,typeBlockLessons.getTitle());
    }
}
