package com.eazybank.servicehubbusinessaccount.mapper;

import com.eazybank.servicehubbusinessaccount.dto.AccountDto;
import com.eazybank.servicehubbusinessaccount.entity.Accounts;

public class AccountsMapper {
    public static AccountDto mapToAccountDto(Accounts account, AccountDto accountDto){
        return AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .build();
    }
    public static Accounts mapToAccount(AccountDto accountDto, Accounts account){
        return Accounts.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .build();
    }
}
