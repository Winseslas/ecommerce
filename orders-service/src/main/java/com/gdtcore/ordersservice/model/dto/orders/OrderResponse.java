package com.gdtcore.ordersservice.model.dto.orders;

import com.gdtcore.ordersservice.model.entities.OrderLine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class OrderResponse {
    private int id;
    private String orderNumber;
    private String description;
    private List<OrderLine> orderLines;
}
