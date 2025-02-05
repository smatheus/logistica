DELETE FROM product_order;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM users;

INSERT INTO users(id, name) VALUES (88, 'Terra Daniel');
INSERT INTO users(id, name) VALUES (80, 'Priscila Leannon');

INSERT INTO orders(id, total_amount, order_date, user_id) values (836, 6829.20, '2021-09-09', 88);
INSERT INTO orders(id, total_amount, order_date, user_id) values (753, 2734.07, '2021-04-10', 80);

INSERT INTO products(id, price) values
(3, 1899.02),
(1, 1760.01),
(5, 1564.21),
(4, 769.09),
(2, 1045.13);

INSERT INTO product_order(order_id, product_id, price) VALUES
(836, 3, 1899.02),
(836, 1, 1756.22),
(836, 3, 253.62),
(836, 5, 1304.06),
(836, 4, 1616.28),
(753, 5, 359.28),
(753, 2, 260.99),
(753, 2, 277.06),
(753, 3, 1836.74);