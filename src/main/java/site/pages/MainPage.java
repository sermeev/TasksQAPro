package site.pages;

import static org.assertj.core.api.Assertions.assertThat;

import common.enums.TypeSection;
import common.webobjects.APage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import common.annotations.Path;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Path("/")
public class MainPage extends APage<MainPage> {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private final String coursesInSectionLocator = "//*[h2 = '%s']";
    private final String titleCourseLocator = "//h5";
    private final String linkToPageCourseLocator = titleCourseLocator+"/ancestor::a";
    private final String costCoursesLocator = "//p[text()='Стоимость в рассрочку']//following-sibling::div";
    private final String costCoursesAltLocator = "//p[contains(text(),  'Полная стоимость')]/following-sibling::div";
    private final String prefixDateStartString = "С ";

    private final String dateStartCourseLocator = String.format("//div/span[contains(text(),'%s')]",prefixDateStartString);

    public List<WebElement> getElementsCourses(String linkToPageCourseLocator, TypeSection typeSection) {
        List<WebElement> linksCourses = getElements(String.format(coursesInSectionLocator+ linkToPageCourseLocator, typeSection.getTitle()));
        return linksCourses;
    }
    public void getElementCourse(TypeSection typeSection, String nameCourse){
        WebElement element = null;
        try {
            element =  getElementsCourses(titleCourseLocator, typeSection).stream().filter((WebElement course)->course.getText().equals(nameCourse)).collect(Collectors.toList()).get(0);
        }catch (IndexOutOfBoundsException exception){
            element = null;
        }
        assertThat(element!=null)
                .as(String.format("Course not found '%1$s' in section '%2$s'",nameCourse,TypeSection.POPULARS.getTitle()))
                .isTrue();
    }
    public WebElement getElementCourse(TypeSection typeSection, int index){
        if(index<0) return null;
        try{
            return getElementsCourses(titleCourseLocator, typeSection).get(index-1);
        } catch (IndexOutOfBoundsException exception){
            return null;
        }

    }
    private LocalDate convertDateStartToLocalDate(String dateStart){
        LocalDate date = LocalDate.now();
        dateStart = dateStart.replace(prefixDateStartString,"")+" "+String.valueOf(date.getYear());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy",new Locale("ru", "RU"));
        date = LocalDate.parse(dateStart, formatter);
        return date;

    }
    public void getCourseByDateStart(TypeSection typeSection, String nameCourse, boolean isBefore){
        ArrayList<LocalDate> dates = new ArrayList<>();
        List<WebElement> datesStartString = getElementsCourses(dateStartCourseLocator, typeSection);
        datesStartString.forEach(element -> dates.add(convertDateStartToLocalDate(element.getText())));
        LocalDate date = dates.stream().reduce((date1, date2)-> ((isBefore?date1.isBefore(date2):date1.isAfter(date2))?date1:date2)).get(); //date1.isAfter(date2)?date1:date2)

        assertThat(nameCourse.equals(getElementCourse(typeSection,dates.indexOf(date)+1).getText()))
                .as(String.format("Course '%1$s' in section '%2$s' not start before all",nameCourse,TypeSection.POPULARS.getTitle()))
                .isTrue();
    }
    private int getCostCourse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements costString = doc.selectXpath(costCoursesLocator);
        if(costString.isEmpty())
            return 0;
        return Integer.parseInt(costString.get(0).text().replaceAll("[^0-9]", ""));
    }
    public int getCostCourseByIndex(TypeSection typeSection, int index) throws IOException {
        ArrayList<LocalDate> dates = new ArrayList<>();
        List<WebElement> linksCourses = getElementsCourses(linkToPageCourseLocator, typeSection);
        return getCostCourse(linksCourses.get(index - 1).getAttribute("href"));
    }

    public void getCostCourseByPrice(TypeSection typeSection, String nameCourse, boolean isMax)  throws IOException {
        getElementCourse(typeSection,nameCourse);
        List<WebElement> linksCourses = getElementsCourses(linkToPageCourseLocator, typeSection);
        List<Integer> costs = new ArrayList<>();
        linksCourses.forEach(linksCourse->{
            try {
                int cost =getCostCourse(linksCourse.getAttribute("href"));
                if(cost>0)
                    costs.add(cost);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        int cost = costs.stream().reduce((cost1, cost2)->((isMax?cost1>cost2:cost1<cost2)?cost1:cost2)).get();

        assertThat(nameCourse.equals(getElementCourse(typeSection, costs.indexOf(cost)+1).getText()))
                .as(String.format("Cost course '%1$s' in section '%2$s' not is minimal",nameCourse,TypeSection.POPULARS.getTitle()))
                .isTrue();
    }

    public MainPage moveAndClickCursor(int x, int y){
        actions.moveByOffset(x,y)
                .click()
                .build()
                .perform();
        return this;
    }

    public void checkResult(){
        assertThat(driver.getCurrentUrl().equals(APage.getUrl()))
                .as("Transfer to another page")
                .isTrue();

    }
}
