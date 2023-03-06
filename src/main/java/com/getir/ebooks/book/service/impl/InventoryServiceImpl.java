package com.getir.ebooks.book.service.impl;

import com.getir.ebooks.book.repository.InventoryRepository;
import com.getir.ebooks.book.entity.Inventory;
import com.getir.ebooks.book.exception.InventoryEntityNotFoundException;
import com.getir.ebooks.book.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.function.Supplier;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public void decreaseAmount(Inventory inventory, int amount) {

        //check quantity
        if(!(amount>0)) {
            throw new ValidationException("should be selected minimum one book to order");
        }

        //check inventory quantity
        if(amount > inventory.getQuantity()) {
            throw new ValidationException("can be selected maximum " + inventory.getQuantity() +" book to order");
        }
        inventory.setQuantity(inventory.getQuantity() - amount);
        inventoryRepository.save(inventory);
    }

    @Override
    public Inventory findInventoryByBookId(Integer bookId) {
        Supplier<InventoryEntityNotFoundException> s =
                () -> new InventoryEntityNotFoundException(bookId + " :bookId not found inventory information");
        return inventoryRepository.findByBook_Id(bookId).orElseThrow(s);
    }
}
