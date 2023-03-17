package com.enigmacamp.tokopakedi.controller;


import com.enigmacamp.tokopakedi.entity.Product;
import com.enigmacamp.tokopakedi.service.ProductService;
import com.enigmacamp.tokopakedi.utils.constant.ApiUrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT_PATH)
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){

        return productService.saveProduct(product);
    }

    @GetMapping
    public List<Product> getProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
    }
}
