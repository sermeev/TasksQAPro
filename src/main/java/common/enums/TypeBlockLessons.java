package common.enums;

public enum TypeBlockLessons {
   POPULARS("Популярные курсы"),
   SPECIALIZATIONS("Специализации"),
   RECOMMENDATIONS("Рекомендации");

    public String getTitle() {
        return title;
    }

    String title;

    TypeBlockLessons(String title) {
        this.title = title;
    }
}
