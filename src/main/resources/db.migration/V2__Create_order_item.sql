CREATE TABLE order_items (
     id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
     product_id UUID NOT NULL,
     quantity INT NOT NULL,
     price NUMERIC(15, 2) NOT NULL,
     order_id UUID NOT NULL,
     CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);
