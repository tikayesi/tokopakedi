package com.enigmacamp.tokopakedi.service;

import com.enigmacamp.tokopakedi.dto.CustomerSearchDTO;
import com.enigmacamp.tokopakedi.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer product);
    Customer updateCustomer(Customer product);
    List<Customer> getAllCustomer();
    Customer getCustomerById(String productId);
    void deleteCustomer(String id);

    Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);

    List<Customer> searchCustomer(String fullName);
}
