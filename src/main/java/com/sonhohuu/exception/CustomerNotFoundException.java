package com.sonhohuu.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Integer id) {
        super("Could not found the customer with id " + id);
    }
}
