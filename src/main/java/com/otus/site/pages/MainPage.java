package com.otus.site.pages;

import com.google.inject.Inject;
import com.otus.common.annotations.Path;
import com.otus.common.enums.TypeSection;
import com.otus.common.webobjects.APage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import com.otus.bdd.support.GuiceScoped;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Path("/")
public class MainPage extends APage<MainPage> {
    @Inject
    public MainPage(GuiceScoped guiceScoped) {
        super(guiceScoped);
    }

    private final String coursesInSectionLocator = "//*[h2 = '%s']";
    private final String titleCourseLocator = "//h5";
    private final String linkToPageCourseLocator = titleCourseLocator+"/ancestor::a";
    private final String costCoursesLocator = "//div[contains(text(),  'Стоимость обучения')]/following-sibling::div/nobr";
    private final String costCoursesAltLocator = "//p[contains(text(),  'Полная стоимость')]/following-sibling::div";
    private final String prefixDateStartString = "С ";

    private final String dateStartCourseLocator = String.format("//div/span[contains(text(),'%s')]",prefixDateStartString);

    public List<WebElement> getElementsCourses(String linkToPageCourseLocator, TypeSection typeSection) {
        List<WebElement> linksCourses = getElements(String.format(coursesInSectionLocator+ linkToPageCourseLocator, typeSection.getTitle()));
        return linksCourses;
    }
    public WebElement getElementCourse(TypeSection typeSection, String nameCourse){
        try {
            return  getElementsCourses(titleCourseLocator, typeSection).stream().filter((WebElement course)->course.getText().equals(nameCourse)).collect(Collectors.toList()).get(0);
        }catch (IndexOutOfBoundsException exception){
            return null;
        }



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
    public String getCourseByDateStart(TypeSection typeSection, boolean isBefore){
        ArrayList<LocalDate> dates = new ArrayList<>();
        List<WebElement> datesStartString = getElementsCourses(dateStartCourseLocator, typeSection);
        datesStartString.forEach(element -> dates.add(convertDateStartToLocalDate(element.getText())));
        LocalDate date = dates.stream().reduce((date1, date2)-> ((isBefore?date1.isBefore(date2):date1.isAfter(date2))?date1:date2)).get(); //date1.isAfter(date2)?date1:date2)
        return getElementCourse(typeSection,dates.indexOf(date)+1).getText();
    }
    private int getCostCourse(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        boolean isSpecialization = !doc.select("main").isEmpty();
        if(isSpecialization)
            return -1;
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

    public String getCostCourseByPrice(TypeSection typeSection, boolean isMax)  throws IOException {
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
        return getElementCourse(typeSection, costs.indexOf(cost)+1).getText();
    }

    public MainPage moveAndClickCursor(int x, int y){
        actions.moveByOffset(x,y)
                .click()
                .build()
                .perform();
        return this;
    }
}
