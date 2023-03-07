package com.getir.ebooks.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.ebooks.book.entity.Book;
import com.getir.ebooks.book.entity.Inventory;
import com.getir.ebooks.book.exception.BookEntityNotFoundException;
import com.getir.ebooks.book.exception.InventoryEntityNotFoundException;
import com.getir.ebooks.book.repository.InventoryRepository;
import com.getir.ebooks.book.service.BookService;
import com.getir.ebooks.book.service.InventoryService;
import com.getir.ebooks.common.model.AppPage;
import com.getir.ebooks.order.entity.Order;
import com.getir.ebooks.order.entity.OrderItem;
import com.getir.ebooks.order.exception.OrderEntityNotFoundException;
import com.getir.ebooks.order.model.OrderDTO;
import com.getir.ebooks.order.repository.OrderRepository;
import com.getir.ebooks.order.service.impl.OrderServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = OrderServiceImpl.class)
class OrderServiceTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    BookService bookService;

    @MockBean
    InventoryService inventoryService;

    @MockBean
    InventoryRepository inventoryRepository;


    @Test
    void whenIsOkFindOrdersByCustomerIdTest() {
        AppPage appPage = new AppPage();
        appPage.setPageNo(1);
        appPage.setPageSize(10);

        Order expectedOrder = getExpectedOrder();

        when(orderRepository.findByCustomer_Id(2, PageRequest.of(appPage.getPageNo(), appPage.getPageSize(), Sort.by("createdAt").descending())))
                .thenReturn(Arrays.asList(expectedOrder));
        final List<Order> orders = orderService.findOrdersByCustomerId(2, appPage);

        assertThat(orders.size()).isEqualTo(expectedOrder.getOrderItems().size());
    }

    @Test
    void whenIsNotFoundFindOrdersByCustomerIdTest() {
        AppPage appPage = new AppPage();
        appPage.setPageNo(1);
        appPage.setPageSize(10);

        when(orderRepository.findByCustomer_Id(2, PageRequest.of(appPage.getPageNo(), appPage.getPageSize(), Sort.by("createdAt").descending())))
                .thenThrow(OrderEntityNotFoundException.class);

        assertThrows(
                OrderEntityNotFoundException.class,
                () ->orderService.findOrdersByCustomerId(2, appPage));

    }

    @Test
    void whenIsOkFindOrderByIdTest() {
        final Order expectedOrder = getExpectedOrder();
        when(orderRepository.findById(1)).thenReturn(Optional.of(expectedOrder));
        final Order order = orderService.findOrderById(1);
        assertThat(order.getOrderDate()).isEqualTo(expectedOrder.getOrderDate());
    }

    @Test
    void whenIsNotFoundFindOrderByIdTest() {
        when(orderRepository.findById(1)).thenThrow(OrderEntityNotFoundException.class);
        assertThrows(
                OrderEntityNotFoundException.class,
                () ->orderService.findOrderById(1));

    }

    @Test
    void whenIsOkFindOrderByDateIntervalTest() {
        final Order expectedOrder = getExpectedOrder();
        when(orderRepository.findAllByOrderDateBetween(any(), any())).thenReturn(Arrays.asList(expectedOrder));
        final List<Order> orders = orderRepository.findAllByOrderDateBetween(new Date(), new Date());
        assertThat(orders.size()).isEqualTo(expectedOrder.getOrderItems().size());
    }

    @Test
    void whenIsEmptyFindOrderByDateIntervalTest() {
        when(orderRepository.findAllByOrderDateBetween(any(), any())).thenReturn(new ArrayList<>());
        final List<Order> orders = orderRepository.findAllByOrderDateBetween(new Date(), new Date());
        assertThat(orders.size()).isEqualTo(0);
    }


    private Book getBook() {
        Book book = new Book();
        book.setId(5);
        book.setPrice(new BigDecimal(10.20));
        return book;
    }

    private Order getOrder() {
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setTotalAmount(new BigDecimal(0));
        return order;
    }

    private Inventory getInventory() {
        Inventory inventory = new Inventory();
        inventory.setId(3);
        inventory.setBook(getBook());
        inventory.setQuantity(4);
        return inventory;
    }

    private Order getExpectedOrder() {
        Order order = getOrder();
        order.setTotalAmount(new BigDecimal(10.20));
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1);
        orderItem.setQuantity(2);
        orderItem.setBook(getBook());
        orderItem.setSubTotal(new BigDecimal(10.20));
        order.setOrderItems(Arrays.asList(orderItem));
        return order;
    }
}
