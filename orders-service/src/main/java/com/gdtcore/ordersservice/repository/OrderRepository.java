package com.gdtcore.ordersservice.repository;

import com.gdtcore.ordersservice.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
