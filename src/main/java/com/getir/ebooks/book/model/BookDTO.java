package com.getir.ebooks.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    @NotEmpty(message = "author cannot be empty")
    private String author;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private BigDecimal price;
    @NotNull(message = "inventory cannot be null")
    private InventoryDTO inventory;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class InventoryDTO {
        private int quantity;
    }
}
