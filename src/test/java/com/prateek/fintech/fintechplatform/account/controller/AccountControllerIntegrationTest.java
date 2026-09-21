package com.prateek.fintech.fintechplatform.account.controller;

import com.prateek.fintech.fintechplatform.customer.entity.Customer;
import com.prateek.fintech.fintechplatform.customer.entity.CustomerStatus;
import com.prateek.fintech.fintechplatform.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import com.prateek.fintech.fintechplatform.account.repository.AccountRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    void setUp() {

        accountRepository.deleteAll();
        customerRepository.deleteAll();

        customer = new Customer();

        customer.setCustomerNumber("CUST-TEST-001");
        customer.setFullName("Test Customer");
        customer.setEmail("testcustomer@example.com");
        customer.setStatus(CustomerStatus.ACTIVE);

        customer = customerRepository.save(customer);
    }

    @Test
    void shouldCreateAccount() throws Exception {

        mockMvc.perform(
                        post(
                                "/api/v1/customers/{customerId}/accounts",
                                customer.getId()
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "accountNumber": "ACC100001",
                                    "currency": "INR"
                                }
                                """)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldRejectBlankAccountNumber() throws Exception {

        mockMvc.perform(
                        post(
                                "/api/v1/customers/{customerId}/accounts",
                                customer.getId()
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "accountNumber": "",
                                    "currency": "INR"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRejectUnsupportedCurrency() throws Exception {

        mockMvc.perform(
                        post(
                                "/api/v1/customers/{customerId}/accounts",
                                customer.getId()
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "accountNumber": "ACC100002",
                                    "currency": "USD"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRejectDuplicateAccount() throws Exception {

        mockMvc.perform(
                        post(
                                "/api/v1/customers/{customerId}/accounts",
                                customer.getId()
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "accountNumber": "ACC100003",
                                    "currency": "INR"
                                }
                                """)
                )
                .andExpect(status().isCreated());

        mockMvc.perform(
                        post(
                                "/api/v1/customers/{customerId}/accounts",
                                customer.getId()
                        )
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                    "accountNumber": "ACC100003",
                                    "currency": "INR"
                                }
                                """)
                )
                .andExpect(status().isConflict());
    }
}


// MUST KNOW — Test data dependency
//We added:
//@Autowired
//private CustomerRepository customerRepository;
//private Customer customer;
//because an account now requires an existing customer.
//Then:
//@BeforeEach
//void setUp() {
//    customerRepository.deleteAll();
//    customer = new Customer();
//    ...
//    customer = customerRepository.save(customer);
//}
//This creates a fresh customer before every test.
//🔥 MUST KNOW — Correct endpoint
//Every account request is now:
//post(
//    "/api/v1/customers/{customerId}/accounts",
//    customer.getId()
//)
//This matches your production controller:
//POST /api/v1/customers/{customerId}/accounts
//🟡 SHOULD KNOW — Why customer.getId() works
//The ID is generated by JPA when:
//customerRepository.save(customer);
//executes.
//So after saving:
//customer.getId()
//contains the generated UUID.