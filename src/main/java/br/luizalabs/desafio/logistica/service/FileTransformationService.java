package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.entity.Product;
import br.luizalabs.desafio.logistica.entity.ProductOrder;
import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.processor.FileProcessor;
import br.luizalabs.desafio.logistica.processor.OrderFileProcessor;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class FileTransformationService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductOrderService productOrderService;

    @Transactional
    public void insertOrders(File file) throws IOException {
        Map<Long, User> userMap = userService.loadExistingUsers();
        Map<Long, Order> orderMap = orderService.loadExistingOrders();
        Map<Long, Product> productMap = productService.loadExistingProducts();

        List<User> newUsers = new ArrayList<>();
        List<Order> newOrders = new ArrayList<>();
        List<Product> newProducts = new ArrayList<>();
        List<ProductOrder> productOrders = new ArrayList<>();

        FileProcessor orderFileProcessor = new OrderFileProcessor(userMap, orderMap, productMap, newUsers, newOrders, newProducts, productOrders);

        try (Stream<String> lines = Files.lines(file.toPath())) {
            lines.forEach(orderFileProcessor::processLine);
        }

        saveEntities(newUsers, newOrders, newProducts, productOrders);
    }

    private void saveEntities(List<User> users, List<Order> orders, List<Product> products, List<ProductOrder> productsOrders) {
        userService.saveAllUsers(users);
        productService.saveAllProducts(products);
        orderService.saveAllOrders(orders);
        productOrderService.saveAllProductsOrders(productsOrders);
    }


}
