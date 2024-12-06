package base.core.model;

import lombok.Data;

@Data
public class Book {
    private Long code;
    private String title;
    private String author;
    private Integer year;

    public Book(Long code, String title, String author, Integer year) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }
}
