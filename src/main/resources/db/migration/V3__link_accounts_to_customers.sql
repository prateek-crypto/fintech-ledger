-- Concept: Foreign key relationship
-- Why: prevent an account from referencing a customer that doesn't exist.

ALTER TABLE accounts
    ADD COLUMN customer_id UUID;

ALTER TABLE accounts
    ADD CONSTRAINT fk_accounts_customer
        FOREIGN KEY (customer_id)
            REFERENCES customers(id);

CREATE INDEX idx_accounts_customer_id
    ON accounts(customer_id);

-- MUST KNOW
-- FOREIGN KEY (customer_id)
-- REFERENCES customers(id)
-- means:
-- An accounts.customer_id value must refer to an existing customers.id.
-- 🟡 SHOULD KNOW
-- The index:
-- CREATE INDEX idx_accounts_customer_id
-- ON accounts(customer_id);
-- helps queries such as:
-- SELECT *
-- FROM accounts
-- WHERE customer_id = ?;
-- This becomes important later when we discuss database performa