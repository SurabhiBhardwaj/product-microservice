package com.example.productMS;

import com.example.productMS.model.Category;
import com.example.productMS.model.Product;
import com.example.productMS.repository.ProductRepos;
import com.example.productMS.service.ProductService;
import com.example.productMS.service.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
class ProductMicroserviceApplicationTests {

    @MockBean
    private ProductRepos productRepos;

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void testGetAllProducts() {
        log.info("ProductMicroserviceApplicationTests -- testCreateProduct");
        String productName = "Test Product";
        float price = 1000;
        int pid = 6;

        Set<Category> categories = new HashSet<>();

        Category c1 = new Category();
        c1.setCategoryName("ELECTRONICS");
        categories.add(c1);

        when(productRepos.findAll()).thenReturn(Stream.of(new Product(pid, productName, price, categories)).collect(Collectors.toList()));

        assertEquals(1, productService.getAllProducts().size());
    }

    @Test
    public void testAddProduct() {
        log.info("ProductMicroserviceApplicationTests -- testAddProduct");
        String productName = "Test Product";
        float price = 1000;
        int pid = 1;

        Set<Category> categories = new HashSet<>();

        Category c1 = new Category();
        c1.setCategoryName("ELECTRONICS");
        categories.add(c1);

        Product productToSave = new Product(pid, productName, price, categories);
        when(productRepos.save(productToSave)).thenReturn(productToSave);
        assertEquals(productToSave, productService.createProduct(productToSave));
    }

    @Test
    public void testDeleteProduct() {
        log.info("ProductMicroserviceApplicationTests -- testDeleteProduct");
        String productName = "Test Product";
        float price = 1000;
        int pid = 1;

        Set<Category> categories = new HashSet<>();

        Category c1 = new Category();
        c1.setCategoryName("ELECTRONICS");
        categories.add(c1);

        Product productToSave = new Product(pid, productName, price, categories);

//        productService.deleteProduct(pid);
//
//        verify(productRepos, times(1)).deleteById(pid);

        //when(productRepos.deleteById(pid)).thenReturn(true);


        willDoNothing().given(productRepos).deleteById(pid);

        // when -  action or the behaviour that we are going test
        productService.deleteProduct(pid);

        // then - verify the output
        verify(productRepos, times(1)).deleteById(pid);

    }

}
