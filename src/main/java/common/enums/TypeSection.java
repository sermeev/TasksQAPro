package common.enums;

public enum TypeSection {
   POPULARS("Популярные курсы"),
   //SPECIALIZATIONS("Специализации"),
   RECOMMENDATIONS("Рекомендации");

    public String getTitle() {
        return title;
    }

    String title;

    TypeSection(String title) {
        this.title = title;
    }
}
