package site.components;

import common.annotations.Component;
import common.exceptions.AnnotationEmptyException;
import common.webobjects.AComponent;
import org.openqa.selenium.WebDriver;
@Component("lessons_new-item")
public class BlockLessonComponent extends AComponent<BlockLessonComponent> {
    public BlockLessonComponent(WebDriver driver) throws AnnotationEmptyException {
        super(driver);
    }
}
