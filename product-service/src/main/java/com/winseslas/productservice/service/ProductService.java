package com.winseslas.productservice.service;

import com.winseslas.productservice.exception.ProductNotFoundException;
import com.winseslas.productservice.model.dto.ProductRequest;
import com.winseslas.productservice.model.dto.ProductResponse;
import com.winseslas.productservice.model.entitie.Product;
import com.winseslas.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        this.productRepository.save(product);
        log.info("Product {} is saved", product.getId());
        return;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> allProducts = this.productRepository.findAll();
        return allProducts.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse getProductById(Long productId) {
        Product product = this.productRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        return mapToProductResponse(product);
    }

    public void updateProduct(Long productId, ProductRequest productRequest) {
        Product existingProduct = this.productRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));


        if (productRequest.getName() != null) {
            existingProduct.setName(productRequest.getName());
        }
        if (productRequest.getDescription() != null) {
            existingProduct.setDescription(productRequest.getDescription());
        }
        if (productRequest.getPrice() != null) {
            existingProduct.setPrice(productRequest.getPrice());
        }
        if (productRequest.getQuantity() != null) {
            existingProduct.setQuantity(productRequest.getQuantity());
        }

        this.productRepository.save(existingProduct);
        log.info("Product {} is updated", existingProduct.getId());
    }

    public void deleteProduct(Long productId) {
        Product existingProduct = this.productRepository.findById(Math.toIntExact(productId))
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
        this.productRepository.delete(existingProduct);
        log.info("Product {} is deleted", productId);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
