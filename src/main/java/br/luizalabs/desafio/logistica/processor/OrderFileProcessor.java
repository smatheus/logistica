package br.luizalabs.desafio.logistica.processor;

import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.entity.Product;
import br.luizalabs.desafio.logistica.entity.ProductOrder;
import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.utils.FileUtils;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class OrderFileProcessor implements FileProcessor{

    private static final FileUtils fileUtils = new FileUtils();

    private final Range<Integer> USER_ID_FIELD_RANGE = Range.closed(0, 10);
    private final Range<Integer> USER_NAME_FIELD_RANGE = Range.closed(10, 55);
    private final Range<Integer> ORDER_ID_FIELD_RANGE = Range.closed(55, 65);
    private final Range<Integer> PRODUCT_ID_FIELD_RANGE = Range.closed(65, 75);
    private final Range<Integer> PRODUCT_PRICE_FIELD_RANGE = Range.closed(75, 87);
    private final Range<Integer> ORDER_DATE_FIELD_RANGE = Range.closed(87, 95);

    private final Map<Long, User> userMap;
    private final Map<Long, Order> orderMap;
    private final Map<Long, Product> productMap;
    private final List<User> newUsers;
    private final List<Order> newOrders;
    private final List<Product> newProducts;
    private final List<ProductOrder> productOrders;

    public OrderFileProcessor(Map<Long, User> userMap,
    Map<Long, Order> orderMap,
    Map<Long, Product> productMap,
    List<User> newUsers,
    List<Order> newOrders,
    List<Product> newProducts, List<ProductOrder> productOrders){
        this.userMap = userMap;
        this.orderMap = orderMap;
        this.productMap = productMap;
        this.newUsers = newUsers;
        this.newOrders = newOrders;
        this.newProducts = newProducts;
        this.productOrders = productOrders;
    }

    @Override
    public void processLine(String line) {
        Long userId = fileUtils.extractLong(line, USER_ID_FIELD_RANGE);
        String userName = fileUtils.extractString(line, USER_NAME_FIELD_RANGE);
        Long orderId = fileUtils.extractLong(line, ORDER_ID_FIELD_RANGE);
        Long productId = fileUtils.extractLong(line, PRODUCT_ID_FIELD_RANGE);
        BigDecimal productPrice = fileUtils.extractBigDecimal(line, PRODUCT_PRICE_FIELD_RANGE);
        LocalDate orderDate = fileUtils.extractDate(line, ORDER_DATE_FIELD_RANGE);

        User user = getUser(userMap, userId, userName, newUsers);
        Order order = getOrder(orderMap, orderId, orderDate, user, newOrders);
        order.setTotalAmount(productPrice);

        Product product = getProduct(productMap, productId, productPrice, newProducts);


        productOrders.add(createProductOrder(order, product, productPrice));
    }

    private ProductOrder createProductOrder(Order order, Product product, BigDecimal productPrice) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setOrder(order);
        productOrder.setProduct(product);
        productOrder.setPrice(productPrice);
        return productOrder;
    }

    private User getUser(Map<Long, User> userMap, Long userId, String name, List<User> users) {
        return userMap.computeIfAbsent(userId, id -> {
            User user = new User(id, name);
            users.add(user);
            return user;
        });
    }

    private Order getOrder(Map<Long, Order> orderMap, Long orderId, LocalDate date, User user, List<Order> orders) {
        return orderMap.computeIfAbsent(orderId, id -> {
            Order order = new Order();
            order.setId(id);
            order.setOrderDate(date);
            order.setUser(user);
            orders.add(order);
            return order;
        });
    }

    private Product getProduct(Map<Long, Product> productMap, Long productId, BigDecimal price, List<Product> products) {
        return productMap.computeIfAbsent(productId, id -> {
            Product product = new Product();
            product.setId(id);
            product.setPrice(price);
            products.add(product);
            return product;
        });
    }
}
