package com.demo.conexion.services;

import com.demo.conexion.entities.ProductsEntity;
import com.demo.conexion.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public ResponseEntity<Map<String, Object>> getAllProducts() {
        Map<String, Object> response = new HashMap<>();
        List<ProductsEntity> productos = productsRepository.findAll();
        response.put("productos", productos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getProductById(UUID id) {
        Map<String, Object> response = new HashMap<>();
        Optional<ProductsEntity> productFound = productsRepository.findById(id);
        if (productFound.isPresent()) {
            response.put("productos", productFound.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("productos", "product not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Map<String, Object>> createProduct(ProductsEntity product) {
        Map<String, Object> response = new HashMap<>();
        product.setId(UUID.randomUUID());
        if (productsRepository.existsById(product.getId())) {
            response.put("Status", "product already exists");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            ProductsEntity newProduct = new productsRepository.save(product);
            response.put("Status", "success");
            response.put("product", newProduct);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }
}
