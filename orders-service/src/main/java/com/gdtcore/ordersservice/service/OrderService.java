package com.gdtcore.ordersservice.service;

import com.gdtcore.ordersservice.exception.OrderNotFoundException;
import com.gdtcore.ordersservice.model.dto.OrderLineRequest;
import com.gdtcore.ordersservice.model.dto.orders.OrderRequestCreate;
import com.gdtcore.ordersservice.model.dto.orders.OrderRequestUpdate;
import com.gdtcore.ordersservice.model.dto.orders.OrderResponse;
import com.gdtcore.ordersservice.model.entities.OrderLine;
import com.gdtcore.ordersservice.model.entities.Orders;
import com.gdtcore.ordersservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderRequestCreate orderRequest) {
        Orders orders = Orders.builder()
                .orderNumber(UUID.randomUUID().toString())
                .description(orderRequest.getDescription())
                .build();

        orderRepository.save(orders);
        log.info("Order {} is saved", orders.getId());
    }

    public OrderResponse getOrderById(Long id) {
        Orders orders = this.orderRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        return mapToOrderResponse(orders);
    }

    public List<OrderResponse> getAllOrders() {
        List<Orders> allOrders = this.orderRepository.findAll();
            return allOrders.stream().map(this::mapToOrderResponse).toList();
    }

    public void updateOrder(long id, OrderRequestUpdate orderRequestUpdate) {
        Orders existingOrder = orderRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));

        if (orderRequestUpdate.getDescription() != null) {
            existingOrder.setDescription(orderRequestUpdate.getDescription());
        }

        if (orderRequestUpdate.getOrderLineRequests() != null) {
            List<OrderLine> orderLines = orderRequestUpdate.getOrderLineRequests()
                    .stream()
                    .map(orderLineRequest -> mapToOrderLineRequest(orderLineRequest, existingOrder))
                    .collect(Collectors.toList());
            existingOrder.setOrderLines(orderLines);
        }
        orderRepository.save(existingOrder);
        log.info("Order {} is updated", existingOrder.getId());
    }

    private OrderLine mapToOrderLineRequest(OrderLineRequest orderLineRequest, Orders orders) {
        log.info("Command " + orders.getId());
        return OrderLine.builder()
                .quantity(orderLineRequest.getQuantity())
                .build();
    }

    private OrderResponse mapToOrderResponse(Orders orders) {
        return OrderResponse.builder()
                .id(orders.getId())
                .orderNumber(orders.getOrderNumber())
                .description(orders.getDescription())
                .orderLines(orders.getOrderLines())
                .build();
    }
}
