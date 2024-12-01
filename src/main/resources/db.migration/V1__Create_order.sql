CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE order (
    id_order UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    status VARCHAR(255) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
)

CREATE TABLE orderClient (
      id_client UUID,
      name VARCHAR(255) NOT NULL,
      street VARCHAR(255) NOT NULL,
      number INT,
      complement VARCHAR(255),
      neighborhood VARCHAR(255),
      city VARCHAR(255) NOT NULL,
      state VARCHAR(100) NOT NULL,
      country VARCHAR(100) NOT NULL,
      zip_code VARCHAR(20) NOT NULL,
      longitude BIGINT,
      latitude BIGINT
)

CREATE TABLE orderProduct (
    id_product UUID,
    productName VARCHAR(255) NOT NULL,
    quantity INT,
    price DECIMAL,
    order_id UUID UNIQUE,

    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES order (id_order)
)