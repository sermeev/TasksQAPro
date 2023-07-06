package site.pages;

import common.enums.TypeSection;
import common.webobjects.APage;
import org.openqa.selenium.WebDriver;
import common.annotations.Path;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Path("/")
public class MainPage extends APage<MainPage> {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String coursesInSectionLocator = "//*[h2 = '%s']";
    private final String titleCourseLocator = "//h5";

    private final String prefixDateStartString = "С ";

    private final String dateStartCourseLocator = String.format("//div/span[contains(text(),'%s')]",prefixDateStartString);


    public WebElement getElementCourseByType(TypeSection typeSection, String nameCourse){

        List<WebElement> courses = getElements(String.format(coursesInSectionLocator+titleCourseLocator,typeSection.getTitle()));
        return  courses.stream().filter((WebElement course)->course.getText().equals(nameCourse)).collect(Collectors.toList()).get(0);

    }
    private LocalDate convertDateStartToLocalDate(String dateStart){
        LocalDate date = LocalDate.now();
        dateStart = dateStart.replace(prefixDateStartString,"")+" "+String.valueOf(date.getYear());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy",new Locale("ru", "RU"));
        date = LocalDate.parse(dateStart, formatter);
        return date;

    }
    public String getCourseByDateStart(TypeSection typeSection, boolean before){
        ArrayList<LocalDate> dates = new ArrayList<>();
        List<WebElement> datesStartString = getElements(String.format(coursesInSectionLocator+dateStartCourseLocator,typeSection.getTitle()));
        datesStartString.forEach(element -> dates.add(convertDateStartToLocalDate(element.getText())));
        LocalDate date = dates.stream().reduce((date1, date2)-> (date1.isAfter(date2)?date1:date2)).get();
        return "";
    }
}
