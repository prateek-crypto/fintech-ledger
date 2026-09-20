package com.prateek.fintech.fintechplatform.account.controller;

import com.prateek.fintech.fintechplatform.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void cleanDatabase() {
        accountRepository.deleteAll();
    }

    @Test
    void shouldCreateAccount() throws Exception {

        mockMvc.perform(
                        post("/api/v1/accounts")
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
                        post("/api/v1/accounts")
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
                        post("/api/v1/accounts")
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
                        post("/api/v1/accounts")
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
                        post("/api/v1/accounts")
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

// @SpringBootTest
//means:
//Load the Spring application for the test.

// @AutoConfigureMockMvc
//means:
//Configure MockMvc so we can test HTTP endpoints.

// mockMvc.perform(...)
//means:
//Simulate an HTTP request.

// .andExpect(status().isCreated())
//means:
//Assert that the API returned HTTP 201.

