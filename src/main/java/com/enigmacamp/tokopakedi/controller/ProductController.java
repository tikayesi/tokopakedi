package com.enigmacamp.tokopakedi.controller;


import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product){

        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getProduct(){
        return productService.getAllProduct();
    }
}
