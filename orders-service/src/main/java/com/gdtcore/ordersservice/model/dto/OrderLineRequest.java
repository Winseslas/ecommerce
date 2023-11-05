package com.gdtcore.ordersservice.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineRequest {
    private Integer quantity;
}
