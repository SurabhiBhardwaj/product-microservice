package com.example.productMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.productMS.model.Product;

@Repository
public interface ProductRepos extends JpaRepository<Product, Integer>{

}