package com.BookTrackerAPI.BookerTacker.book.services;

import com.BookTrackerAPI.BookerTacker.book.model.Book;
import com.BookTrackerAPI.BookerTacker.exceptions.BookCreationException;
import com.BookTrackerAPI.BookerTacker.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book create(Book book) throws BookCreationException;
    Book getById(Long id) throws BookNotFoundException;
    List<Book> getAllBooks();
    Optional<Book> getByBookName(String bookName) throws BookNotFoundException;
    List<Book> getByAuthorLastName(String authorLastName) throws BookNotFoundException;
    Book update(Long id, Book bookDetail) throws BookNotFoundException;
    void delete(Long id) throws BookNotFoundException;

}
