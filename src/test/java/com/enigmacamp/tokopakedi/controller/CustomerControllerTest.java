package com.enigmacamp.tokopakedi.controller;

import com.enigmacamp.tokopakedi.entity.Customer;
import com.enigmacamp.tokopakedi.service.CustomerService;
import com.enigmacamp.tokopakedi.utils.customResponse.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;


    @Test
    public void testGetCustomerById() {
        // mock data
        String id = "1";
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFullName("John");

        // mock behavior
        when(customerService.getCustomerById(id)).thenReturn(customer);

        // perform request
        Customer response = customerController.getCustomerById(id);

        // verify result
        assertEquals(customer, response);

        // verify behavior
        verify(customerService, times(1)).getCustomerById(id);
    }

    @Test
    public void testGetCustomerByIdNotFound() {
        // mock data
        String id = "1";

        // mock behavior
        when(customerService.getCustomerById(id)).thenReturn(null);

        // perform request
        Customer response = customerController.getCustomerById(id);

        // verify result
        assertNull(response);

        // verify behavior
        verify(customerService, times(1)).getCustomerById(id);
    }

    @Test
    void getCustomers() {
    }

    @Test
    void addCustomer() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setFullName("tes");
        customer.setEmail("tes");
        customer.setAddress("tes");

        Response res = new Response<>();
        res.setMessage("aaaa");
        res.setData(customer);

        // mock behavior
        when(customerService.saveCustomer(customer)).thenReturn(customer);

        // perform request
        ResponseEntity<Response<Customer>> response = customerController.addCustomer(customer);

        // verify result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(res.getData(), response.getBody().getData());

        // verify behavior
        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    void searchCustomer() {
    }
}