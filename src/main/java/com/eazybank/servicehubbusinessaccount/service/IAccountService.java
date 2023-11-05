package com.eazybank.servicehubbusinessaccount.service;

import com.eazybank.servicehubbusinessaccount.dto.CustomerDto;

public interface IAccountService {
    /**
     *
     * @param customerDto-customerDto Object
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchByMobileNumber(String mobileNumber);
}
