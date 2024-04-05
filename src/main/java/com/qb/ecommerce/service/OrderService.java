package com.qb.ecommerce.service;

import com.qb.ecommerce.dto.OrderDTO;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
public interface OrderService {
    List<OrderDTO> getAllOrders();

    Optional<OrderDTO> getOrder(Long orderId);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(OrderDTO orderDTO);

    void deleteOrder(Long orderId);
}