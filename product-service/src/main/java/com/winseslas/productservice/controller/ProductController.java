package com.winseslas.productservice.controller;

import com.winseslas.productservice.model.dto.ProductRequest;
import com.winseslas.productservice.model.dto.ProductResponse;
import com.winseslas.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                                        
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        this.productService.createProduct(productRequest);
    }

    @GetMapping("/read-all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @GetMapping("/read-one/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Long productId) {
        return this.productService.getProductById(productId);
    }

    @PatchMapping("/update/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
        this.productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        this.productService.deleteProduct(productId);
    }
}
