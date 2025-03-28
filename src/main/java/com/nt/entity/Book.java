package com.nt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String author;
    private String publisher;
    private int publishedYear;

    private int availableCopies;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "'}";
    }

}
