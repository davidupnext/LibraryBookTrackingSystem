package com.BookTrackerAPI.BookerTacker.book.controller;

import com.BookTrackerAPI.BookerTacker.book.model.Book;
import com.BookTrackerAPI.BookerTacker.book.repo.BookRepo;
import com.BookTrackerAPI.BookerTacker.book.services.BookService;
import com.BookTrackerAPI.BookerTacker.exceptions.BookCreationException;
import com.BookTrackerAPI.BookerTacker.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private BookService bookService;

    @Autowired
    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) throws BookCreationException {
        book = bookService.create(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Iterable<Book>> getAll() {
        Iterable<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) throws BookNotFoundException {
        Book book = bookService.getById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> updated(@PathVariable("id") Long id, @RequestBody Book bookDetail) throws BookNotFoundException {
        Book book = bookService.update(id, bookDetail);
        return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) throws BookNotFoundException {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
