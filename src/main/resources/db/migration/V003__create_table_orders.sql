CREATE TABLE orders (
     id bigserial not null,
     total_amount decimal(10,2),
     order_date date,
     user_id bigInt not null,

     primary key(id),
     CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);