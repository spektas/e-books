package com.getir.ebooks.customer.controller;

import com.getir.ebooks.customer.entity.Customer;
import com.getir.ebooks.customer.service.CustomerService;
import com.getir.ebooks.common.model.AppPage;
import com.getir.ebooks.order.entity.Order;
import com.getir.ebooks.order.mapper.OrderMapper;
import com.getir.ebooks.order.model.OrderDTO;
import com.getir.ebooks.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/register")
    public ResponseEntity<Customer> registerNewCustomer(@RequestBody @Valid Customer customer) {
        return new ResponseEntity(customerService.saveCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Integer customerId, AppPage appPage) {
        List<Order> orders = orderService.findOrdersByCustomerId(customerId, appPage);
        final List<OrderDTO> orderDTOList =
                orders.stream().map(orderMapper::fromOrderEntityToOrderDTO).collect(Collectors.toList());
        return new ResponseEntity(orderDTOList, HttpStatus.OK);
    }
}
