package com.BookTrackerAPI.BookerTacker.services;

import com.BookTrackerAPI.BookerTacker.book.model.Book;
import com.BookTrackerAPI.BookerTacker.book.repo.BookRepo;
import com.BookTrackerAPI.BookerTacker.book.services.BookService;
import com.BookTrackerAPI.BookerTacker.exceptions.BookCreationException;
import com.BookTrackerAPI.BookerTacker.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    @Autowired
    BookService bookService;
    @MockBean
    private BookRepo bookRepo;

    private Book book01;
    private Book savedBook01;
    private Book savedBook02;

    @BeforeEach
    public void setUp01() {
        book01 = new Book("Lightning Thief", "Rick", "Riordan");
        savedBook01 = new Book("Lightning Thief", "Rick", "Riordan");
        savedBook01.setId(1L);
        savedBook02 = new Book("Heroes of Olympus", "Rick", "Riordan");
        savedBook02.setId(2L);
    }

    @Test
    @DisplayName("Create a book")
    public void createABookTest01() throws BookCreationException {
        BDDMockito.doReturn(Optional.empty()).when(bookRepo).findByBookName(ArgumentMatchers.any());
        BDDMockito.doReturn(savedBook01).when(bookRepo).save(book01);
        Book book = bookService.create(book01);
        Assertions.assertNotNull(book.getId());
    }

    @Test
    @DisplayName("Get by Id")
    public void getByIdTest01() throws BookNotFoundException {
        BDDMockito.doReturn(Optional.of(savedBook01)).when(bookRepo).findById(1l);
        Book book = bookService.getById(1l);
        Assertions.assertNotNull(book);

    }

    @Test
    @DisplayName("Get All Books")
    public void getAllPeopleTest01() {
        List<Book> books = new ArrayList<>();
        books.add(savedBook01);
        books.add(savedBook02);
        BDDMockito.doReturn(books).when(bookRepo).findAll();
        List<Book> actualbooks = bookService.getAllBooks();
        Integer expectedSize = 2;
        Integer actualSize = actualbooks.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }
}