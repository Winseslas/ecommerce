package com.gdtcore.ordersservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;

    @CreatedDate
    @Column(name = "createAt", nullable = false)
    @JsonIgnore
    private Instant createAt;

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
}
