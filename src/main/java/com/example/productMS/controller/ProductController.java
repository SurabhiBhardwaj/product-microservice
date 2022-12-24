package com.example.productMS.controller;

import com.example.productMS.exception.TokenNotValidException;
import com.example.productMS.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.productMS.model.Product;
import com.example.productMS.service.ProductServiceImpl;


@RestController
@RequestMapping("/products")

public class ProductController {

    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    TokenService tokenService;


    @GetMapping("{pid}")
    public Optional<Product> getProduct(@PathVariable int pid, @RequestHeader String token) throws IOException {
        if (tokenService.verifyToken(token).equals("VALID TOKEN")) {
            return productServiceImpl.getProduct(pid);
        } else {
            throw new TokenNotValidException("Token is not valid");
        }

    }

    @GetMapping
    public List<Product> getAllProducts() {

        return productServiceImpl.getAllProducts();
    }


    @PostMapping("/")
    public Product createProduct(@RequestBody Product product, @RequestHeader String token) throws IOException {

        if (tokenService.verifyToken(token).equals("VALID TOKEN")) {
            return productServiceImpl.createProduct(product);
        } else {
            throw new TokenNotValidException("Token is not valid");
        }
    }

    @PutMapping("/id/{pid}")
    public String editProduct(@PathVariable int pid, @RequestBody Product product, @RequestHeader String token) throws IOException {
        if(tokenService.verifyToken(token).equals("VALID TOKEN")) {
            return productServiceImpl.editProduct(product);
        }else {
            throw new TokenNotValidException("Token is not valid");
        }
    }

    @DeleteMapping("/id/{pid}")
    public boolean deleteProduct(@PathVariable int pid, @RequestHeader String token) throws IOException{
        if(tokenService.verifyToken(token).equals("VALID TOKEN")) {
            return productServiceImpl.deleteProduct(pid);
        }else {
            throw new TokenNotValidException("Token is not valid");
        }
    }

}
