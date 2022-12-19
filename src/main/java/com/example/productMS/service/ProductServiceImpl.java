package com.example.productMS.service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.productMS.exception.NoSuchProductExistsException;
import com.example.productMS.exception.ProductAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productMS.model.Product;
import com.example.productMS.repository.ProductRepos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ProductServiceImpl implements ProductService {

	private final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepos productRepos;
	
	@Override
	public Optional<Product> getProduct(int pid) {
		return Optional.ofNullable(productRepos.findById(pid).orElseThrow(() -> new NoSuchProductExistsException(
						"NO PRODUCT PRESENT WITH ID = " + pid)));
	}

	@Override
	public List<Product> getAllProducts() {
		
		List<Product> newList=productRepos.findAll().stream().collect(Collectors.toList());

		return newList;
	}

	
	public String createProduct(Product product) {
		Product existingProduct
				= productRepos.findById(product.getPid())
				.orElse(null);
		if (existingProduct == null) {
			productRepos.save(product);
			return "Product added successfully";
		}
		else
			throw new ProductAlreadyExistsException(
					"Product already exists!!");


//		Product toBeSavedProduct = new Product();
//		String statusMsg = "";
//
//		toBeSavedProduct.setProductName(product.getProductName());
//		toBeSavedProduct.setPrice(product.getPrice());
//		toBeSavedProduct.setCategories(product.getCategories());
//
//
//		try {
//			productRepos.save(toBeSavedProduct);
//			statusMsg = "Data Inserted sucessfully";
//		} catch (Exception e) {
//			statusMsg = "Exception occured while Inserting the Data";
//		}
//		logger.info("***END--createmyproduct()-- product{}");
//		return product;
		// Global Exception handling , input data validations 
	}

	
	public String editProduct(Product product) {
		Product existingProduct
				= productRepos.findById(product.getPid())
				.orElse(null);
		if (existingProduct == null)
			throw new NoSuchProductExistsException(
					"No Such Product exists!!");
		else {
			existingProduct.setPid(product.getPid());
			existingProduct.setProductName(product.getProductName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setCategories(product.getCategories());
			productRepos.save(existingProduct);
			return "Record updated Successfully";
//		String statusMsg = "";
//		Product toBeUpdatedProduct = new Product();
//
//		toBeUpdatedProduct.setPid(product.getPid());
//		toBeUpdatedProduct.setProductName(product.getProductName());
//		toBeUpdatedProduct.setPrice(product.getPrice());
//		toBeUpdatedProduct.setCategories(product.getCategories());
//
//		try {
//			productRepos.save(toBeUpdatedProduct);
//			statusMsg = "Data Updated sucessfully";
//		} catch (Exception e) {
//			statusMsg = "Exception occured while Updating the Data";
//		}
//
//		logger.info("***END--updatemyproduct()-- product{}" + statusMsg);
//		return statusMsg;
		}
	}

	public boolean deleteProduct(int pid) {

		boolean success = false;
		Product existingProduct
				= productRepos.findById(pid)
				.orElse(null);

		if(existingProduct==null)
		{
		throw new NoSuchProductExistsException("Sorry, Product cannot be deleted with id : " + pid);
		}
		else
		{
			productRepos.deleteById(pid);
			success = true;
			return success;
		}

	}
}
