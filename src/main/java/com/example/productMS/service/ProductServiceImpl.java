package com.example.productMS.service;

import java.util.List;
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
        logger.info("ProductServiceImpl -- getProduct");
        return Optional.ofNullable(productRepos.findById(pid).orElseThrow(() -> new NoSuchProductExistsException(
                "NO PRODUCT PRESENT WITH ID = " + pid)));
    }

    @Override
    public List<Product> getAllProducts() {
        logger.info("ProductServiceImpl -- getAllProducts");
        List<Product> newList = productRepos.findAll().stream().collect(Collectors.toList());
        return newList;
    }


    public Product createProduct(Product product) {
        logger.info("ProductServiceImpl -- createProduct");
        Product existingProduct = productRepos.findById(product.getPid()).orElse(null);
        if (existingProduct == null) {
            return productRepos.save(product);
        } else
            throw new ProductAlreadyExistsException("Product already exists!!");
    }


    public String editProduct(Product product) {
        logger.info("ProductServiceImpl -- editProduct");
        Product existingProduct = productRepos.findById(product.getPid()).orElse(null);
        if (existingProduct == null)
            throw new NoSuchProductExistsException("No Such Product exists!!");
        else {
            existingProduct = new Product().builder()
                    .pid(product.getPid())
                    .productName(product.getProductName())
                    .price(product.getPrice())
                    .categories(product.getCategories())
                    .build();

            productRepos.save(existingProduct);
            return "Record updated Successfully";
        }
    }

    public boolean deleteProduct(int pid) {
        logger.info("ProductServiceImpl -- deleteProduct");
        boolean success = false;
        Product existingProduct = productRepos.findById(pid).orElse(null);
        if (existingProduct == null) {
            throw new NoSuchProductExistsException("Sorry, Product cannot be deleted with id : " + pid);
        } else {
            productRepos.deleteById(pid);
            success = true;
            return success;
        }

    }
}