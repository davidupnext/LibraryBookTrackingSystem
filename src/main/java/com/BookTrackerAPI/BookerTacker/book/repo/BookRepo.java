package com.BookTrackerAPI.BookerTacker.book.repo;

import com.BookTrackerAPI.BookerTacker.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
    @Override
    Optional<Book> findById(Long Id);
    Iterable<Book> findByAuthorLastName(String AuthorLastName);
    Optional<Book> findByBookName(String bookName);
}
