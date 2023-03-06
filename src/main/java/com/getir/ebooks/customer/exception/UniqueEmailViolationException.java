package com.getir.ebooks.customer.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class UniqueEmailViolationException extends DataIntegrityViolationException {
    public UniqueEmailViolationException(String msg) {
        super(msg);
    }
}
