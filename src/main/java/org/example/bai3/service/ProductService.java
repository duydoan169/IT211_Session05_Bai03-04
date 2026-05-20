package org.example.bai3.service;

import lombok.RequiredArgsConstructor;
import org.example.bai3.model.Product;
import org.example.bai3.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product findProductById(Long id){
        return productRepository.findProductById(id);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product putProduct(Long id, Product product){
        product.setId(id);
        return productRepository.save(product);
    }

    public Product patchProduct(Long id, Product product) {
        Product target = findProductById(id);

        if (product.getName() != null)  target.setName(product.getName());
        if (product.getPrice() != null) target.setPrice(product.getPrice());

        return productRepository.save(target);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}