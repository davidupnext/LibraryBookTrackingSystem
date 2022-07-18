package com.BookTrackerAPI.BookerTacker.exceptions;

import com.BookTrackerAPI.BookerTacker.book.model.Book;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(String msg){
        super(msg);
    }
}
