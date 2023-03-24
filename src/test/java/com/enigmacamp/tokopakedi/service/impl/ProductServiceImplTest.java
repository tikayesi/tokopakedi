package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.repository.ProductRepository;
import com.enigmacamp.tokopakedi.utils.exception.DataNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

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
        Product product = new Product("01", "Ciki", 1000, 5);
      //mock
        when(productRepository.save(product)).thenReturn(product);

       // perform
        Product savedProduct = productService.saveProduct(product);

        //verify result
        assertNotNull(savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getProductName(), savedProduct.getProductName());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void updateProduct() {

        // mock behavior for findById()
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // mock behavior for save()
        when(productRepository.save(product)).thenReturn(product);

        // perform update
        Product updatedProduct = productService.updateProduct(product);

        // verify result
        assertNotNull(updatedProduct);
        assertEquals(product.getId(), updatedProduct.getId());
        assertEquals(product.getProductName(), updatedProduct.getProductName());

        // verify behavior
        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testUpdateProductNotFound() {

        // mock behavior for findById()
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());

        // perform update
        Throwable example = assertThrows(DataNotFoundException.class, () -> {
            productService.updateProduct(product);
        });

        assertEquals("Data with id 01 is not found", example.getMessage());

        // verify behavior
        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, never()).save(product);
    }

    @Test
    void getAllProduct() {
        List<Product> listOfProduct = new ArrayList<>();
        Product product = new Product("01", "sepatu", 200000, 5);
        Product product1 = new Product("02", "Tas", 500000, 2);
        listOfProduct.add(product);
        listOfProduct.add(product1);

        when(productRepository.findAll()).thenReturn(listOfProduct);

        List<Product> productList = productService.getAllProduct();

        assertEquals(2, productList.size());

        verify(productRepository, times(1)).findAll();

    }

    // positif test case
    @Test
    void getProductById() {
        when(productRepository.findById("01")).thenReturn(Optional.of(Optional.of(product).get()));
        Product product = productService.getProductById("01");
        assertEquals("Shampo", product.getProductName());
        assertEquals(1000, product.getProductPrice());
        verify(productRepository, times(1)).findById("01");

    }

    // negative test case
    @Test
    void getProductByIdNotPresent(){
        Throwable throwable = assertThrows(NoSuchElementException.class, () -> productService.getProductById("p01"));
        assertEquals("No value present", throwable.getMessage());
        verify(productRepository, times(1)).findById("p01");
    }



    @Test
    void deleteProduct() {
        String id = "01";
        productService.deleteProduct(id);
        verify(productRepository, times(1)).deleteById(id);
    }
}