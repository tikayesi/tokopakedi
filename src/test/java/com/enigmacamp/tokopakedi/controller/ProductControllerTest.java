package com.enigmacamp.tokopakedi.controller;

import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    private Product product;

    @BeforeEach
    void setup() {
        product = new Product("01", "Shampo", 1000, 1);
    }

    @AfterEach
    void cleanUp() {
        product = new Product();
    }

    @Test
    void saveProduct() {
        // mock behavior
        when(productService.saveProduct(product)).thenReturn(product);

        // perform request
        Product response = productController.saveProduct(product);

        // verify result
//        assertEquals(HttpStatus.CREATED, response.);
//        assertEquals(myEntity, response.getBody());
//
//        // verify behavior
//        verify(productService, times(1)).save(myEntity);
    }

    @Test
    void getProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}