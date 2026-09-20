CREATE TABLE accounts (
                          id UUID PRIMARY KEY,

                          account_number VARCHAR(20) NOT NULL UNIQUE,

                          balance NUMERIC(19, 2) NOT NULL DEFAULT 0,

                          currency VARCHAR(3) NOT NULL DEFAULT 'INR',

                          status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                          version BIGINT NOT NULL DEFAULT 0,

                          created_at TIMESTAMP WITH TIME ZONE NOT NULL,

                          updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);