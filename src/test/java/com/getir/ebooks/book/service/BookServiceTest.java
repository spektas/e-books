package com.getir.ebooks.book.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.ebooks.book.entity.Book;
import com.getir.ebooks.book.exception.BookEntityNotFoundException;
import com.getir.ebooks.book.exception.UniqueBookVioaltionException;

import com.getir.ebooks.book.repository.BookRepository;
import com.getir.ebooks.book.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BookServiceImpl.class)
class BookServiceTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    BookService bookService;

    @MockBean
    private BookRepository bookRepository;


    @Test
    void whenNotFoundEntityFindBookByIdTest() {
        when(bookRepository.findBookById(1)).thenThrow(BookEntityNotFoundException.class);
        assertThrows(BookEntityNotFoundException.class, () -> bookService.findBookById(1));
    }

    @Test
    void whenFindBookByIdIsOkTest() {
        when(bookRepository.findBookById(1)).thenReturn(java.util.Optional.of(new Book()));
        bookService.findBookById(1);
    }

    @Test
    void whenNewSaveBookIsOkTest() {
        when(bookRepository.existsByAuthorAndName(anyString(), anyString())).thenReturn(false);

        // only set author and name
        Book book = new Book();
        book.setAuthor("author");
        book.setName("bookName");

        bookService.saveBook(new Book());
    }

    @Test
    void whenAlreadySavedBookTest() {
        when(bookRepository.existsByAuthorAndName(anyString(), anyString())).thenReturn(true);

        // only set author and name
        Book book = new Book();
        book.setAuthor("author");
        book.setName("bookName");

        assertThrows(UniqueBookVioaltionException.class, () -> bookService.saveBook(book));
    }
}
