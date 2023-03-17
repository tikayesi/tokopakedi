package com.enigmacamp.tokopakedi.repository;

import com.enigmacamp.tokopakedi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> , JpaSpecificationExecutor<Customer> {
    List<Customer> findCustomerByFullNameContainingIgnoreCase(String nameCriteria);
}
