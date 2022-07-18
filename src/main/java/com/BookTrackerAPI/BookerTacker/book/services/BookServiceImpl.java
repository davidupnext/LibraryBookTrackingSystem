package com.BookTrackerAPI.BookerTacker.book.services;

import com.BookTrackerAPI.BookerTacker.book.model.Book;
import com.BookTrackerAPI.BookerTacker.book.repo.BookRepo;
import com.BookTrackerAPI.BookerTacker.exceptions.BookCreationException;
import com.BookTrackerAPI.BookerTacker.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepo bookRepo;
    @Autowired
    public BookServiceImpl(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }
    @Override
    public Book create(Book book) throws BookCreationException {
        Optional<Book> optional = bookRepo.findByBookName(book.getBookName());
        if(optional.isPresent())
            throw new BookCreationException("User with username exists " + book.getAuthorLasName());
        return bookRepo.save(book);
    }

    @Override
    public Book getById(Long id) throws BookNotFoundException {
       Book book= bookRepo.findById(id)
               .orElseThrow(() -> new BookNotFoundException("There is No Such Book" + id));
       return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public List<Book> getByAuthorLastName(String authorLastName) throws BookNotFoundException {
        List<Book> books = (List) bookRepo.findByAuthorLastName(authorLastName);
        if(books.size() == 0)
            throw new BookNotFoundException("No book with that author's last name " + authorLastName);
        return books;
    }
    @Override
    public Optional<Book> getByBookName(String bookName) throws BookNotFoundException{
        Optional<Book> optional = bookRepo.findByBookName(bookName);
        if(optional.isEmpty()){
            throw new BookNotFoundException("No such book");
        }
        return optional;
    }


    @Override
    public Book update(Long id, Book bookDetail) throws BookNotFoundException {
        Book savedBook = getById(id);
        savedBook.setBookName(bookDetail.getBookName());
        savedBook.setAuthorLasName(bookDetail.getAuthorLasName());
        return bookRepo.save(savedBook);
    }

    @Override
    public void delete(Long id) throws BookNotFoundException {

        Book book = getById(id);
        bookRepo.delete(book);
    }
}
