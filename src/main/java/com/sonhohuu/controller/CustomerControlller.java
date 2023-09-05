package com.sonhohuu.controller;

import com.sonhohuu.Main;
import com.sonhohuu.exception.CustomerNotFoundException;
import com.sonhohuu.model.Customer;
import com.sonhohuu.reponsitory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("http://localhost:3000/")
public class CustomerControlller {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("customers/{id}")
    Customer getCustomerById(@PathVariable Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(id));
    }

    @PostMapping("/customer")
    Customer newCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @PutMapping("customer/{id}")
    Customer updateCustomer(@RequestBody Customer updateCustomer, @PathVariable Integer id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(updateCustomer.getName());
                    customer.setAge(updateCustomer.getAge());
                    customer.setEmail(updateCustomer.getEmail());
                    return customerRepository.save(customer);
                }).orElseThrow(()-> new CustomerNotFoundException(id));
    }
    @DeleteMapping("customer/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(id);
        }
        customerRepository.deleteById(id);
        return "Delected the customer with id " + id;
    }
}
