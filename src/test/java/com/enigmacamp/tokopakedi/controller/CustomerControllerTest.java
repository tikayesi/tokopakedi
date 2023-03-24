package com.enigmacamp.tokopakedi.controller;

import com.enigmacamp.tokopakedi.entity.Customer;
import com.enigmacamp.tokopakedi.service.CustomerService;
import com.enigmacamp.tokopakedi.utils.customResponse.PageResponseWrapper;
import com.enigmacamp.tokopakedi.utils.customResponse.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getCustomerById() {
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
    void getCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId("01");
        customer1.setFullName("tes");
        customer1.setAddress("jalan");
        Customer customer2 = new Customer();
        customer2.setId("02");
        customer2.setFullName("tes2");
        customer2.setAddress("jalan jalan");
        Page<Customer> page = new PageImpl<>(Arrays.asList(customer1, customer2));
        given(customerService.getCustomerPerPage(any(), any())).willReturn(page);

        // act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .header("Authorization", "Bearer validToken")
                        .param("page", "0")
                        .param("size", "2")
                        .param("sort-by", "fullName")
                        .param("direction", "ASC")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // assert
        ObjectMapper mapper = new ObjectMapper();
        PageResponseWrapper<Customer> response = mapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<PageResponseWrapper<Customer>>() {}
        );
        System.out.println("RESPONSE: "+ response);
        assertEquals(2, response.getData().size());
        assertEquals("Adam", response.getData().get(0).getFullName());
        assertEquals("tangerang", response.getData().get(0).getAddress());
        assertEquals("Aji Arno", response.getData().get(1).getFullName());
        assertEquals("tangerang", response.getData().get(1).getAddress());
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