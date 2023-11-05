package com.gdtcore.ordersservice.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineResponse {
    private int id;
    private Integer quantity;
}
