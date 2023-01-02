package com.udupa.stockservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<String> createProduct(@RequestBody ProductEntity productEntity) {
        Integer id = productService.createProduct(productEntity);
        if(id != null) {
            return ResponseEntity.ok("Added product with Id " + id);
        }
        return ResponseEntity.ok("Couldn't add product.");
    }
}
