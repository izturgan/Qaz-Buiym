package com.qb.ecommerce.service.impl;

import com.qb.ecommerce.config.MapperConfiguration;
import com.qb.ecommerce.dto.OrderDTO;
import com.qb.ecommerce.exception.ResourceNotFoundException;
import com.qb.ecommerce.model.Order;
import com.qb.ecommerce.repository.OrderRepository;
import com.qb.ecommerce.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperConfiguration mapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> mapper.getMapper().map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> mapper.getMapper().map(order, OrderDTO.class));
    }

    // TODO: Complete business logic of create, update, delete methods when payment system is integrated
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapper.getMapper().map(orderDTO, Order.class);
        order.setCreatedDate(LocalDate.now());
        order = orderRepository.save(order);
        return mapper.getMapper().map(order, OrderDTO.class);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        mapper.getMapper().map(orderDTO, existingOrder);
        existingOrder = orderRepository.save(existingOrder);
        return mapper.getMapper().map(existingOrder, OrderDTO.class);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(existingOrder);
    }
}