package com.gdtcore.inventoryservice.model.dto;

import lombok.*;

@Builder
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
    private Integer quantity;
}
