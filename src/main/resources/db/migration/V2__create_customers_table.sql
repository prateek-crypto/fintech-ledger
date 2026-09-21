CREATE TABLE customers (
                           id UUID PRIMARY KEY,

                           customer_number VARCHAR(20) NOT NULL UNIQUE,

                           full_name VARCHAR(100) NOT NULL,

                           email VARCHAR(150) NOT NULL UNIQUE,

                           status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

                           created_at TIMESTAMP WITH TIME ZONE NOT NULL,

                           updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);