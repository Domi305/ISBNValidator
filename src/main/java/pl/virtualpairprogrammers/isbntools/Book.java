package pl.virtualpairprogrammers.isbntools;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Book {

    private String isbn;
    private String title;
    private String author;
}
