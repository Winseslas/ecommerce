package com.gdtcore.ordersservice.model.dto.orders;

import com.gdtcore.ordersservice.model.dto.OrderLineRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class OrderRequestUpdate extends OrderRequestCreate {
    private List<OrderLineRequest> orderLineRequests;
}
