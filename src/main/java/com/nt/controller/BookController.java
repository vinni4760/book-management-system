package com.nt.controller;

import com.nt.dto.BookDto;
import com.nt.dto.UserDto;
import com.nt.entity.Book;
import com.nt.request.AddNewBookRequest;
import com.nt.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable String authorName) {
        List<BookDto> books = bookService.getBooksByAuthorName(authorName);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDto>> getBookByTitle(@PathVariable String title) {
        return new ResponseEntity<>(bookService.getByName(title),HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<BookDto> addBook(@RequestBody AddNewBookRequest addNewBookRequest) {
        BookDto savedBook = bookService.addBook(addNewBookRequest);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody AddNewBookRequest addNewBookRequest) {
        Book updatedBook = bookService.updateBook(id, addNewBookRequest);
        return ResponseEntity.ok(updatedBook);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

    @PutMapping("/borrowbook")
    public ResponseEntity<UserDto> borrowBook(@RequestParam("userId") Integer uid,
                                              @RequestParam("bookId") Integer bid){
        try{
          return new ResponseEntity<UserDto>(  bookService.borrowBook(uid,bid),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
           return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        }
    }
    @GetMapping("/borrowdbooks")
    public ResponseEntity<List<BookDto>> getBorrowedBooks(@RequestParam("userId") Integer id){
        try{
            return new ResponseEntity<>(bookService.getBorrowedBooks(id),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
