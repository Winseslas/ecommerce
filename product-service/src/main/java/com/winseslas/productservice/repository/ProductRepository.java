package com.winseslas.productservice.repository;

import com.winseslas.productservice.model.entitie.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Product entity.
 * Extends the JpaRepository interface provided by Spring Data JPA.
 * The method is implemented by the Spring Data JPA framework.
 *
 * @author Winseslas
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
