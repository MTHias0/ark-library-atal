package base.core.model;

import lombok.Data;

@Data
public class Book {
    private Long code;
    private String title;
    private String author;
    private Integer year;
}
