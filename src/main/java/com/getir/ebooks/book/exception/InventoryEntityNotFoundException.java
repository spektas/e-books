package com.getir.ebooks.book.exception;

import javax.persistence.EntityNotFoundException;

public class InventoryEntityNotFoundException extends EntityNotFoundException {
    public InventoryEntityNotFoundException(String message) {
        super(message);
    }
}
