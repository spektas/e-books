package com.getir.ebooks.book.service;

import com.getir.ebooks.book.entity.Book;
import com.getir.ebooks.book.model.BookDTO;

public interface BookService {
    Book updateBook(BookDTO bookDTO);
    Book findBookById(Integer bookId);
    Book saveBook(Book fromBookDtoToBookEntity);
}
