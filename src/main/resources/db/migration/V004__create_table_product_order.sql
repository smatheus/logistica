CREATE TABLE product_order (
     id bigserial not null,
     order_id bigint not null,
     product_id bigint not null,
     price decimal(10,2)

     primary key(id)
     CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
     CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
);