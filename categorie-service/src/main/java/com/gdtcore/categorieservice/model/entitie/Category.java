package com.gdtcore.categorieservice.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Builder
@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = true)
    private String description;

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

//    @OneToMany(mappedBy = "category")
//    private Set<Product> products;
}
