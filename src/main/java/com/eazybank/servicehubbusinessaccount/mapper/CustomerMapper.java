package com.eazybank.servicehubbusinessaccount.mapper;

import com.eazybank.servicehubbusinessaccount.dto.CustomerDto;
import com.eazybank.servicehubbusinessaccount.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
        return CustomerDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNumber(customer.getMobileNumber())
                .build();
    }
    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer){
        return Customer.builder()
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .mobileNumber(customerDto.getMobileNumber())
                .build();
    }
}
