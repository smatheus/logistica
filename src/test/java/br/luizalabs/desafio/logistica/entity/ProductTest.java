package br.luizalabs.desafio.logistica.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ProductTest {

    private Product product1;
    private Product product2;
    private ProductOrder mockProductOrder;

    @BeforeEach
    public void setup() {
        mockProductOrder = mock(ProductOrder.class);

        product1 = new Product();
        product1.setId(1L);
        product1.setPrice(BigDecimal.valueOf(100.00));
        product1.setOrderedProducts(new HashSet<>());

        product2 = new Product();
        product2.setId(1L);
        product2.setPrice(BigDecimal.valueOf(100.00));
        product2.setOrderedProducts(new HashSet<>());
    }

    @Test
    public void shouldBeEquals() {
        assertEquals(product1, product2);
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    public void shouldBeDifferent() {
        product2.setId(2L);
        assertNotEquals(product1, product2);
    }

    @Test
    public void shouldTestPrice() {
        assertEquals(BigDecimal.valueOf(100.00), product1.getPrice());
    }

    @Test
    public void shouldAddProductOrder() {
        Set<ProductOrder> productOrders = new HashSet<>();
        productOrders.add(mockProductOrder);
        product1.setOrderedProducts(productOrders);

        assertTrue(product1.getOrderedProducts().contains(mockProductOrder));
    }
}

