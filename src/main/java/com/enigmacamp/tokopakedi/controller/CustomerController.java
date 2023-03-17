package com.enigmacamp.tokopakedi.controller;

import com.enigmacamp.tokopakedi.dto.CustomerSearchDTO;
import com.enigmacamp.tokopakedi.entity.Customer;
import com.enigmacamp.tokopakedi.service.CustomerService;
import com.enigmacamp.tokopakedi.utils.constant.ApiUrlConstant;
import com.enigmacamp.tokopakedi.utils.customResponse.PageResponseWrapper;
import com.enigmacamp.tokopakedi.utils.customResponse.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER_PATH)
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public PageResponseWrapper<Customer> getCustomers(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(name = "size", defaultValue = "5") Integer size,
                                                      @RequestParam(name = "sort-by", defaultValue = "fullName") String sortBy,
                                                      @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                      @RequestParam(name = "name") String fullName,
                                                      @RequestParam(name = "address") String address
    ){

        CustomerSearchDTO customerSearchDTO = new CustomerSearchDTO();
        customerSearchDTO.setCustomerFullName(fullName);
        customerSearchDTO.setCustomerAddress(address);
        Sort sorting = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(pageNumber, size, sorting);
               Page<Customer> customerPage = customerService.getCustomerPerPage(pageable, customerSearchDTO);
               return new PageResponseWrapper<Customer>(customerPage);
    }

    @PostMapping
    public ResponseEntity<Response<Customer>> addCustomer(@RequestBody Customer customer){
        String message = "Data resources customer has been inserted";
        Response<Customer> response = new Response<>();
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/search")
    public List<Customer> searchCustomer(@RequestParam(name = "fullname") String customerName){
        return customerService.searchCustomer(customerName);
    }
}
