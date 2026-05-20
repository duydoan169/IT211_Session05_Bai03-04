package org.example.bai3.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai3.model.Product;
import org.example.bai3.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product target = productService.findProductById(id);
        if (target == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(target, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        if (product == null || product.getName() == null || product.getPrice() == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Long id, @RequestBody Product product){
        Product target = productService.findProductById(id);
        if (target == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productService.putProduct(id, product), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Product product){
        Product target = productService.findProductById(id);
        if (target == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(productService.patchProduct(id, product), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        Product target = productService.findProductById(id);
        if (target == null){
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}