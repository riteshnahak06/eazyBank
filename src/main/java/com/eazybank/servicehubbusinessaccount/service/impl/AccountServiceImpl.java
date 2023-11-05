package com.eazybank.servicehubbusinessaccount.service.impl;

import com.eazybank.servicehubbusinessaccount.constant.AccountsConstant;
import com.eazybank.servicehubbusinessaccount.dto.AccountDto;
import com.eazybank.servicehubbusinessaccount.dto.CustomerDto;
import com.eazybank.servicehubbusinessaccount.entity.Accounts;
import com.eazybank.servicehubbusinessaccount.entity.Customer;
import com.eazybank.servicehubbusinessaccount.exception.CustomerAlreadyExistException;
import com.eazybank.servicehubbusinessaccount.exception.ResourceNotFoundException;
import com.eazybank.servicehubbusinessaccount.mapper.AccountsMapper;
import com.eazybank.servicehubbusinessaccount.mapper.CustomerMapper;
import com.eazybank.servicehubbusinessaccount.repository.AccountRepository;
import com.eazybank.servicehubbusinessaccount.repository.CustomerRepository;
import com.eazybank.servicehubbusinessaccount.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistException("Customer already exist for the given mobile number "+customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountsMapper.mapToAccountDto(accounts,new AccountDto()));
        return customerDto;
    }

    private Accounts createNewAccount(Customer customer) {
        Long generateAccountNumber=1000000000L+new Random().nextInt(900000000);
        Accounts account =new Accounts();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountType(AccountsConstant.SAVINGS);
        account.setAccountNumber(generateAccountNumber);
        account.setBranchAddress(AccountsConstant.ADDRESS);
        return account;
    }
}
