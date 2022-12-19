package com.example.productMS.controller;

import com.example.productMS.exception.ProductAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.productMS.model.Product;

import com.example.productMS.service.ProductServiceImpl;

@RestController
@RequestMapping("/products")

public class ProductController {

	private final static Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	ProductServiceImpl productServiceImpl;


	@GetMapping("{pid}")
	//@GetMapping("/id/{pid}")
	public Optional<Product> getProduct(@PathVariable int pid) {

		//return productServiceImpl.getProduct(pid);
		Optional<Product> pr = productServiceImpl.getProduct(pid);
		if(pr==null)
		{
			throw new ProductAlreadyExistsException();
		}
		return pr;
	}

	@GetMapping
	public List<Product> getAllProducts() {

		return productServiceImpl.getAllProducts();
	}


	@PostMapping("/")
	public String createProduct(@RequestBody Product product)
	{
		return productServiceImpl.createProduct(product);
	}

	@PutMapping("/id/{pid}")
	public String editProduct(@PathVariable int pid, @RequestBody Product product) {
		return productServiceImpl.editProduct(product);
	}

	@DeleteMapping("/id/{pid}")
	public boolean deleteProduct(@PathVariable int pid) {
		return productServiceImpl.deleteProduct(pid);
	}

}

