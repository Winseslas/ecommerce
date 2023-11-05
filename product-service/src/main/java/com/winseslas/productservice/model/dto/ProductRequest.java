package com.winseslas.productservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * Represents a product request in the application.
 *
 * @author Winseslas
 */

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The description of the product.
     */
    private String description;

    /**
     * The price of the product.
     */
    private BigDecimal price;

    /**
     * The quantity of the product.
     */
    private Integer quantity;
}
