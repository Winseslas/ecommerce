package com.gdtcore.inventoryservice.model.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer quantity;

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
