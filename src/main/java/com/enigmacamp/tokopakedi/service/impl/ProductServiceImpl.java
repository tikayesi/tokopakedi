package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.repository.ProductRepository;
import com.enigmacamp.tokopakedi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }
}
