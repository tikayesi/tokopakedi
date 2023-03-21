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
        productService.saveProduct(product);
        verify(productRepository, times(1)).save(product);

    }

    @Test
    void updateProduct() {
        when(productRepository.findById("01")).thenReturn(Optional.of(Optional.of(product).get()));
        Product product = productService.getProductById("01");

        productService.updateProduct(product);
        verify(productRepository, times(1)).save(product);

    }

    @Test
    void updateProductIdNotFound() {
        Throwable ex = assertThrows(DataNotFoundException.class, () -> productService.updateProduct(product));
        System.out.println(ex.getMessage());
        assertEquals("Data with id 01 is not found", ex.getMessage());

    }

    @Test
    void getAllProduct() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product("01", "sisir", 1000, 1);
        Product product2 = new Product("02", "serit", 500, 1);
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);
        List<Product> productList = productService.getAllProduct();
        assertEquals(2, productList.size());
        verify(productRepository, times(1)).findAll();

    }

    @Test
    void getProductById() {
        when(productRepository.findById("01")).thenReturn(Optional.of(Optional.of(product).get()));
        Product product = productService.getProductById("01");
        assertEquals("Shampo", product.getProductName());
        assertEquals(1000, product.getProductPrice());

    }

    @Test
    void  getProductByIdNotPresent() {
        Throwable ex = assertThrows(NoSuchElementException.class, () -> productService.getProductById("p01"));
        System.out.println(ex.getMessage());
        assertEquals("No value present", ex.getMessage());
    }


    @Test
    void deleteProduct() {
        productService.deleteProduct("s12");
        verify(productRepository, times(1)).deleteById("s12");

    }
}