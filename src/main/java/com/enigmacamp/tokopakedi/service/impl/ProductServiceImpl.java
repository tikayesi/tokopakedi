package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.repository.ProductRepository;
import com.enigmacamp.tokopakedi.service.ProductService;
import com.enigmacamp.tokopakedi.utils.exception.DataNotFoundException;
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
        if(productRepository.findById(product.getId()).isPresent()){
            return saveProduct(product);
        }else {
            throw new DataNotFoundException("Data with id "+ product.getId() + " is not found");
        }
    }

    @Override
    public List<Product> getAllProduct() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {

        return productRepository.findById(id).get();
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
