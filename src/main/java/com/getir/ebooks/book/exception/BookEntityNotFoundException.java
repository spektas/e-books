package com.getir.ebooks.book.exception;

import javax.persistence.EntityNotFoundException;

public class BookEntityNotFoundException extends EntityNotFoundException {
    public BookEntityNotFoundException(String message) {
        super(message);
    }
}
