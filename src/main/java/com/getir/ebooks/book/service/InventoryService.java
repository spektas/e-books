package com.getir.ebooks.book.service;

import com.getir.ebooks.book.entity.Inventory;

public interface InventoryService {
    void decreaseAmount(Inventory inventory, int amount);

    Inventory findInventoryByBookId(Integer bookId);
}
