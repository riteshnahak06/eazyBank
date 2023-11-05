package com.eazybank.servicehubbusinessaccount.controller;

import com.eazybank.servicehubbusinessaccount.constant.AccountsConstant;
import com.eazybank.servicehubbusinessaccount.dto.CustomerDto;
import com.eazybank.servicehubbusinessaccount.dto.ResponseDto;
import com.eazybank.servicehubbusinessaccount.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    private IAccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201,
                        AccountsConstant.MESSAGE_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchByMobileNumber(@RequestParam String mobileNumber){
        CustomerDto customerDto = accountService.fetchByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }
}
