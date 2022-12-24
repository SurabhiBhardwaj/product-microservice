package com.example.productMS.service;

import java.util.List;
import java.util.Optional;

import com.example.productMS.model.Product;

public interface ProductService {

    public Optional<Product> getProduct(int pid);

    public List<Product> getAllProducts();

    public Product createProduct(Product product);

    public String editProduct(Product product);

    public boolean deleteProduct(int pid);
}
