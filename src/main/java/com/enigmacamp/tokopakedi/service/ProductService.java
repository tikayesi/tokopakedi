package com.enigmacamp.tokopakedi.service;

import com.enigmacamp.tokopakedi.entity.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getAllProduct();
    Product getProductById(String id);
    void deleteProduct(String id);
}
