package br.luizalabs.desafio.logistica.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class UserTest {

    private User user1;
    private User user2;
    private Order mockOrder;

    @BeforeEach
    public void setup() {
        mockOrder = mock(Order.class);

        user1 = new User(1L, "John Doe");
        user1.setOrders(new HashSet<>());

        user2 = new User(1L, "John Doe");
        user2.setOrders(new HashSet<>());
    }

    @Test
    public void shouldBeEquals() {
        assertEquals(user1, user2);
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void shouldBeDifferent() {
        user2.setId(2L);
        assertNotEquals(user1, user2);
    }

    @Test
    public void shouldHaveSameName() {
        assertEquals("John Doe", user1.getName());
    }

    @Test
    public void shouldAddOrder() {
        Set<Order> orders = new HashSet<>();
        orders.add(mockOrder);
        user1.setOrders(orders);

        assertTrue(user1.getOrders().contains(mockOrder));
    }
}
