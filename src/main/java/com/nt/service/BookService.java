package com.nt.service;

import com.nt.dto.BookDto;
import com.nt.dto.UserDto;
import com.nt.entity.Book;
import com.nt.entity.User;
import com.nt.repository.IBookRepository;
import com.nt.repository.IUserRepository;
import com.nt.request.AddNewBookRequest;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository iBookRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private UserServiceimpl userServiceimpl;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDto addBook(AddNewBookRequest addNewBookRequest) {
        Book book = new Book();
        book.setTitle(addNewBookRequest.getTitle());
        book.setAuthor(addNewBookRequest.getAuthor());
        book.setPublisher(addNewBookRequest.getPublisher());
        book.setAvailableCopies(addNewBookRequest.getAvailableCopies());
        book.setPublishedYear(addNewBookRequest.getPublishedYear());
       return getConvertedDto(iBookRepository.save(book));
    }

    @Override
    public List<BookDto> getBooksByAuthorName(String authorname) {
      List<BookDto> bookDto =  convertoDto(iBookRepository.findAllByAuthor(authorname));
        return bookDto;
    }

    @Override
    public List<BookDto> getByName(String bookname) {
        return convertoDto(iBookRepository.findAllByTitle(bookname));
    }

    @Override
    public Book updateBook(Integer bookId, AddNewBookRequest updatebook) {
        Optional<Book> book = iBookRepository.findById(bookId);
        if(book.isPresent()){
            Book existingbook = book.get();
            existingbook.setTitle(updatebook.getTitle());
            existingbook.setPublisher(updatebook.getPublisher());
            existingbook.setAuthor(updatebook.getAuthor());
            existingbook.setPublishedYear(updatebook.getPublishedYear());
            existingbook.setAvailableCopies(updatebook.getAvailableCopies());
          return  iBookRepository.save(existingbook);
        }
        else
            throw new RuntimeException("No Book Found With Id "+bookId);
    }

    @Override
    public String deleteBook(Integer bookId) {
        Optional<Book> book =  iBookRepository.findById(bookId);
       if(book.isPresent())
           iBookRepository.delete(book.get());
       else
           throw new RuntimeException("Book Not found with id "+bookId);
        return "Book deleted with Id "+bookId;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return convertoDto(iBookRepository.findAll());
    }

    @Transactional
    @Override
    public UserDto borrowBook(Integer userId, Integer bookId) {
     User user = iUserRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id "+userId));
     Book book = iBookRepository.findById(bookId).orElseThrow(()->new RuntimeException("book not found with id "+bookId));
        System.out.println(user+" "+book);
       user.getBooks().add(book);
      return userServiceimpl.convertodto(iUserRepository.save(user));
    }


    @Override
    public List<BookDto> getBorrowedBooks(Integer userId) {
      CustomUserDetails customUserDetails1 = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Integer id =   customUserDetails1.getId();
        System.out.println(id+" "+userId);
        User user = iUserRepository.findById(id).orElseThrow(()->new RuntimeException(""));
       Set<Book> books =   user.getBooks();
        System.out.println(books);
       return books.stream().map(this::getConvertedDto).collect(Collectors.toList());
    }


    public BookDto getConvertedDto(Book book){
        return modelMapper.map(book,BookDto.class);
    }

    public List<BookDto> convertoDto(List<Book> books){
       return books.stream().map(this::getConvertedDto)
               .collect(Collectors.toList());
    }
}
