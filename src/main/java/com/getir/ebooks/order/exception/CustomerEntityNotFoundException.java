package com.getir.ebooks.order.exception;

import javax.persistence.EntityNotFoundException;

public class CustomerEntityNotFoundException extends EntityNotFoundException {
    public CustomerEntityNotFoundException(String message) {
        super(message);
    }
}
