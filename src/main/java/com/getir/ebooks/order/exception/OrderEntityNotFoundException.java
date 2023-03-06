package com.getir.ebooks.order.exception;

import javax.persistence.EntityNotFoundException;


public class OrderEntityNotFoundException extends EntityNotFoundException {
    public OrderEntityNotFoundException(String message) {
        super(message);
    }
}
