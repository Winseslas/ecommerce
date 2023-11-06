package com.gdtcore.inventoryservice.model.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private Long id;
    private String code;
    private Integer quantity;
}
