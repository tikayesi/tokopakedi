package com.enigmacamp.tokopakedi.service.impl;

import com.enigmacamp.tokopakedi.dto.CustomerSearchDTO;
import com.enigmacamp.tokopakedi.entity.Customer;
import com.enigmacamp.tokopakedi.repository.CustomerRepository;
import com.enigmacamp.tokopakedi.service.CustomerService;
import com.enigmacamp.tokopakedi.spesification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId).get();
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }

    @Override
    public List<Customer> searchCustomer(String fullName) {
        return customerRepository.findCustomerByFullNameContainingIgnoreCase(fullName);
    }
}
