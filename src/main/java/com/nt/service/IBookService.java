package com.nt.service;

import com.nt.dto.BookDto;
import com.nt.dto.UserDto;
import com.nt.entity.Book;
import com.nt.request.AddNewBookRequest;
import java.util.List;

public interface IBookService {
    public BookDto addBook(AddNewBookRequest addNewBookRequest);
    public List<BookDto> getBooksByAuthorName(String authorname);
    public List<BookDto> getByName(String bookname);
    public Book updateBook(Integer id,AddNewBookRequest updatebook);
    public String deleteBook(Integer bookId);
    public List<BookDto> getAllBooks();
    public UserDto borrowBook(Integer userId, Integer bookId);
    public List<BookDto> getBorrowedBooks(Integer userId);
}
