package br.luizalabs.desafio.logistica.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class OrderTest {

    private Order order1;
    private Order order2;
    private User mockUser;
    private ProductOrder mockProductOrder;

    @BeforeEach
    public void setup() {
        mockUser = mock(User.class);
        mockProductOrder = mock(ProductOrder.class);

        order1 = new Order(1L, mockUser, new HashSet<>(), BigDecimal.ZERO, LocalDate.now());
        order2 = new Order(1L, mockUser, new HashSet<>(), BigDecimal.ZERO, LocalDate.now());
    }

    @Test
    public void shouldBeEquals() {
        assertEquals(order1, order2);
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    public void shouldBeDifferent() {
        order2.setId(2L);
        assertNotEquals(order1, order2);
    }

    @Test
    public void testUserAssociation() {
        assertEquals(mockUser, order1.getUser());
    }

    @Test
    public void shouldSetTotalAmount() {
        BigDecimal totalAmount = BigDecimal.valueOf(50.00);
        order1.setTotalAmount(totalAmount);
        assertEquals(totalAmount, order1.getTotalAmount());
    }

    @Test
    public void shouldSetOrderedProducts() {
        Set<ProductOrder> productOrders = new HashSet<>();
        productOrders.add(mockProductOrder);
        order1.setOrderedProducts(productOrders);

        assertEquals(order1.getOrderedProducts(), productOrders);
    }

    @Test
    public void shouldSetOrderDate() {
        LocalDate today = LocalDate.now();
        order1.setOrderDate(today);
        assertEquals(today, order1.getOrderDate());
    }

    @Test
    public void shouldSetUser(){
        User user = new User(2L, "Usuario teste");
        order1.setUser(user);
        assertEquals(order1.getUser(), user);
    }
}
