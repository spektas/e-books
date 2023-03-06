package com.getir.ebooks.order.service;

import com.getir.ebooks.common.model.AppPage;
import com.getir.ebooks.order.model.OrderDTO;
import com.getir.ebooks.order.entity.Order;
import com.sun.istack.NotNull;

import java.util.Date;
import java.util.List;

public interface OrderService {
    Order saveOrder(Order order, @NotNull List<OrderDTO.OrderItemDTO> orderItems);

    List<Order> findOrdersByCustomerId(Integer customerId, AppPage appPage);

    Order findOrderById(Integer orderId);

    List<Order> findOrderByDateInterval(Date fromDate, Date toDate);
}
