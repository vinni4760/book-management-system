package com.nt.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AddNewBookRequest {
    private String title;
    private String author;
    private String publisher;
    private int publishedYear;
    private int availableCopies;
}
