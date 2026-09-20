package com.prateek.fintech.fintechplatform.account.service;

import com.prateek.fintech.fintechplatform.account.dto.AccountResponse;
import com.prateek.fintech.fintechplatform.account.dto.CreateAccountRequest;
import com.prateek.fintech.fintechplatform.account.entity.Account;
import com.prateek.fintech.fintechplatform.account.entity.AccountStatus;
import com.prateek.fintech.fintechplatform.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.prateek.fintech.fintechplatform.common.exception.AccountAlreadyExistsException;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest request) {

        if (accountRepository.existsByAccountNumber(request.accountNumber())) {
            throw new AccountAlreadyExistsException(
                    "Account number already exists"
            );
        }

        Account account = new Account();

        account.setAccountNumber(request.accountNumber());
        account.setCurrency(request.currency());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);

        Account savedAccount = accountRepository.save(account);

        return new AccountResponse(
                savedAccount.getId(),
                savedAccount.getAccountNumber(),
                savedAccount.getBalance(),
                savedAccount.getCurrency(),
                savedAccount.getStatus(),
                savedAccount.getCreatedAt(),
                savedAccount.getUpdatedAt()
        );
    }
}