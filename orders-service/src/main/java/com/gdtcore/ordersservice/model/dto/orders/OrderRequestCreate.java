package com.gdtcore.ordersservice.model.dto.orders;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestCreate {
    private String description;
}
