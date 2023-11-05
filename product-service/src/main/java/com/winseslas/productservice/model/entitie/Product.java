package com.winseslas.productservice.model.entitie;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Represents a product in the application.
 *
 * @author Winseslas
 */


@Builder
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    /**
     * The unique identifier of the product.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the product.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * The description of the product.
     */
    @Column(nullable = true)
    private String description;

    /**
     * The price of the product.
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * The quantity of the product.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * The date of creation of the product.
     */
    @CreatedDate
    @Column(name = "createAt", nullable = false)
    @JsonIgnore
    private Instant createAt;

    /**
     * The date of modification of the product.
     */
    @LastModifiedDate
    @Column(name = "updateAt")
    @JsonIgnore
    private Instant updateAt;

    @PrePersist
    private void initializeCreateAt() {
        this.createAt = Instant.now();
        this.updateAt = Instant.now();
    }

    @PreUpdate
    private void initializeUpdateAt() {
        this.updateAt = Instant.now();
    }

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Category category;
}
