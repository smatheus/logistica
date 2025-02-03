package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.entity.Product;
import br.luizalabs.desafio.logistica.entity.ProductOrder;
import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.repository.OrderRepository;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import br.luizalabs.desafio.logistica.repository.ProductRepository;
import br.luizalabs.desafio.logistica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void insertOrders(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");


        List<User> users = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<ProductOrder> productOrders = new ArrayList<>();

        reader.lines().forEach(orderInput -> {
            Long userId = Long.parseLong(orderInput.substring(0, 10).trim());
            String userName = orderInput.substring(10, 55).trim();
            Long orderId = Long.parseLong(orderInput.substring(55, 65).trim());
            Long productId = Long.parseLong(orderInput.substring(65,75).trim());
            BigDecimal productPrice = new BigDecimal(orderInput.substring(75,87).trim());
            LocalDate orderDate = LocalDate.parse(orderInput.substring(87,95).trim(), formatter);

            User user = users.stream().filter(u -> u.getId().equals(userId)).findFirst()
                    .orElseGet(() -> {
                        User newUser = new User(userId, userName);
                        users.add(newUser);
                        return newUser;
                    });
            Order order = orders.stream().filter(o -> o.getId().equals(orderId)).findFirst().orElseGet(() -> {
                Order newOrder = new Order();
                newOrder.setId(orderId);
                newOrder.setOrderDate(orderDate);
                newOrder.setUser(user);
                orders.add(newOrder);
                return newOrder;
            });
            order.setTotalAmount(productPrice);


            Product product = products.stream().filter(p -> p.getId().equals(productId)).findFirst()
                    .orElseGet(() -> {
                        Product newProduct = new Product();
                        newProduct.setId(productId);
                        newProduct.setPrice(productPrice);
                        products.add(newProduct);
                        return newProduct;
                    });

            ProductOrder productOrder = new ProductOrder();
            productOrder.setOrder(order);
            productOrder.setProduct(product);
            productOrder.setPrice(productPrice);
            productOrders.add(productOrder);

        });

        userRepository.saveAll(users);
        productRepository.saveAll(products);
        orderRepository.saveAll(orders);
        productOrderRepository.saveAll(productOrders);
    }

}
