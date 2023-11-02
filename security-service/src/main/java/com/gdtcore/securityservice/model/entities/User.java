package com.gdtcore.securityservice.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Builder
@Entity
@Setter
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
