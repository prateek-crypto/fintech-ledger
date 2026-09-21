package com.prateek.fintech.fintechplatform.account.service;

import com.prateek.fintech.fintechplatform.account.dto.AccountResponse;
import com.prateek.fintech.fintechplatform.account.dto.CreateAccountRequest;
import com.prateek.fintech.fintechplatform.account.entity.Account;
import com.prateek.fintech.fintechplatform.account.entity.AccountStatus;
import com.prateek.fintech.fintechplatform.account.repository.AccountRepository;
import com.prateek.fintech.fintechplatform.common.exception.AccountAlreadyExistsException;
import com.prateek.fintech.fintechplatform.common.exception.CustomerNotFoundException;
import com.prateek.fintech.fintechplatform.customer.entity.Customer;
import com.prateek.fintech.fintechplatform.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountService(
            AccountRepository accountRepository,
            CustomerRepository customerRepository
    ) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public AccountResponse createAccount(
            UUID customerId,
            CreateAccountRequest request
    ) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found: " + customerId
                        )
                );

        if (accountRepository.existsByAccountNumber(
                request.accountNumber())) {

            throw new AccountAlreadyExistsException(
                    "Account number already exists"
            );
        }

        Account account = new Account();

        account.setAccountNumber(request.accountNumber());
        account.setCurrency(request.currency());
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);

        account.setCustomer(customer);

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

    @Transactional(readOnly = true)
    public List<AccountResponse> getAccountsByCustomer(UUID customerId) {

        customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found: " + customerId
                        )
                );

        return accountRepository.findByCustomerId(customerId)
                .stream()
                .map(account -> new AccountResponse(
                        account.getId(),
                        account.getAccountNumber(),
                        account.getBalance(),
                        account.getCurrency(),
                        account.getStatus(),
                        account.getCreatedAt(),
                        account.getUpdatedAt()
                ))
                .toList();
    }
}
// readOnly = true?
//
//🟡 SHOULD KNOW
//
//This operation only reads data.
//
//@Transactional(readOnly = true)
//
//communicates that the transaction isn't intended to modify the database.
//
//🔥 MUST KNOW
//
//For read operations:
//
//@Transactional(readOnly = true)
//
//For operations that modify data:
//
//@Transactional
//Why .stream().map(...)?
//
//🔥 MUST KNOW — DTO mapping
//
//Database returns:
//
//List<Account>
//
//but our API should return:
//
//List<AccountResponse>
//
//So:
//
//Account Entity
//     ↓
//   mapping
//     ↓
//AccountResponse DTO
//
//This prevents us from exposing JPA entities directly through the API.