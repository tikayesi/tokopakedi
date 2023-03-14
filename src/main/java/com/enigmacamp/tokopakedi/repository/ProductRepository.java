package com.enigmacamp.tokopakedi.repository;

import com.enigmacamp.tokopakedi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    //SELECT product_id,product_name,product_price, stock WHERE product_id = ?
    // findAll()
    // INSERT INTO (product_id,product_name,product_price, stock) VALUES (?,?,?,?);
    // save()
}
