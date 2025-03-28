package com.nt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    private int publishedYear;
    private int availableCopies;
}
