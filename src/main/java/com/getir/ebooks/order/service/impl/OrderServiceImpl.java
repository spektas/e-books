package com.getir.ebooks.order.service.impl;

import com.getir.ebooks.common.model.AppPage;
import com.getir.ebooks.book.entity.Book;
import com.getir.ebooks.book.entity.Inventory;
import com.getir.ebooks.book.service.BookService;
import com.getir.ebooks.book.service.InventoryService;
import com.getir.ebooks.order.entity.Order;
import com.getir.ebooks.order.entity.OrderItem;
import com.getir.ebooks.order.exception.OrderEntityNotFoundException;
import com.getir.ebooks.order.model.OrderDTO;
import com.getir.ebooks.order.repository.OrderRepository;
import com.getir.ebooks.order.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final InventoryService inventoryService;

    public OrderServiceImpl(OrderRepository orderRepository, BookService bookService, InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.inventoryService = inventoryService;
    }

    @Override
    @Transactional
    public Order saveOrder(Order order, @NotNull List<OrderDTO.OrderItemDTO> orderItemDTOList) {
        for (OrderDTO.OrderItemDTO orderItemDTO : orderItemDTOList) {
            final Book book = bookService.findBookById(orderItemDTO.getBookId());
            final Inventory inventory = inventoryService.findInventoryByBookId(orderItemDTO.getBookId());

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(orderItemDTO.getQuantity());

            //update inventory
            inventoryService.decreaseAmount(inventory, orderItemDTO.getQuantity());

            BigDecimal subTotal = book.getPrice().multiply(BigDecimal.valueOf(orderItemDTO.getQuantity()));
            orderItem.setSubTotal(subTotal);

            //add order item
            order.setTotalAmount(order.getTotalAmount().add(subTotal));
            order.addOrderItem(orderItem);
        }
       return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByCustomerId(Integer customerId, AppPage appPage) {
        return orderRepository.findByCustomer_Id(
                customerId, PageRequest.of(appPage.getPageNo(), appPage.getPageSize(), Sort.by("createdAt").descending()));
    }

    @Override
    public Order findOrderById(Integer orderId) {
        Supplier<OrderEntityNotFoundException> s =
                () -> new OrderEntityNotFoundException(orderId + " orderId: not found");
        return orderRepository.findById(orderId).orElseThrow(s);
    }

    @Override
    public List<Order> findOrderByDateInterval(Date fromDate, Date toDate) {
        return orderRepository.findAllByOrderDateBetween(fromDate, toDate);
    }
}
