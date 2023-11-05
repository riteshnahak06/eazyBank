package com.eazybank.servicehubbusinessaccount.repository;

import com.eazybank.servicehubbusinessaccount.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts,Long> {
    Optional<Accounts> findByCustomerId(Long customerId);
}
