package com.nt.repository;

import com.nt.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Integer> {
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByTitle(String title);
    }
