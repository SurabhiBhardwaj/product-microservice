package com.example.productMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productMS.model.Category;


public interface CategoryRepos extends JpaRepository<Category, Integer>{

}
