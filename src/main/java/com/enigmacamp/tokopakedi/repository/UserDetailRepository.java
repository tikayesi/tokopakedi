package com.enigmacamp.tokopakedi.repository;

import com.enigmacamp.tokopakedi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<Customer, String> {
}
