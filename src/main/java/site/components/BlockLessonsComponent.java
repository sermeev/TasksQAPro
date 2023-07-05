package site.components;

import common.annotations.Component;
import common.exceptions.AnnotationEmptyException;
import common.webobjects.AComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

@Component("//div[@class = 'lessons']")
public class BlockLessonsComponent extends AComponent<BlockLessonsComponent> {
    private final static String TITLE_LOCATOR = "/preceding-sibling::div[@class='subtitle-new' and text()='%s']";
    public BlockLessonsComponent(WebDriver driver, String title) throws AnnotationEmptyException {
        super(driver,String.format(TITLE_LOCATOR,title));
    }
    public List<BlockLessonComponent> getListBlockLesson(){
        List<BlockLessonComponent> lessonComponents = new ArrayList<>();
        getChildElements(getComponentEntity(),"lessons_new-item").stream().peek((WebElement webElement)-> {
            try {
                lessonComponents.add(new BlockLessonComponent(driver));
            } catch (AnnotationEmptyException e) {
                throw new RuntimeException(e);
            }
        });
        return lessonComponents;
    }
}
