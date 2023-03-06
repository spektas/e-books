package com.getir.ebooks.book.mapper;

import com.getir.ebooks.book.model.BookDTO;
import com.getir.ebooks.book.entity.Book;
import com.getir.ebooks.book.entity.Inventory;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book fromBookDtoToBookEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setAuthor(bookDTO.getAuthor());
        book.setName(bookDTO.getName());
        book.setPrice(bookDTO.getPrice());

        // inventory
        final BookDTO.InventoryDTO inventoryDTO = bookDTO.getInventory();
        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryDTO.getQuantity());

        book.setInventory(inventory);

        return book;
    }

    public BookDTO fromBookEntityToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setName(book.getName());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setId(book.getId());

        final Inventory inventory = book.getInventory();

        // inventory
        final BookDTO.InventoryDTO inventoryDTO = new BookDTO.InventoryDTO();
        inventoryDTO.setQuantity(inventory.getQuantity());

        bookDTO.setInventory(inventoryDTO);

        return bookDTO;
    }
}
