package com.example.productMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productMS.model.Category;


public interface CategoryRepos extends JpaRepository<Category, Integer> {

}