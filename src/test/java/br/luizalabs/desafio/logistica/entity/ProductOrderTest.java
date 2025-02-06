package br.luizalabs.desafio.logistica.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

public class ProductOrderTest {

    private ProductOrder productOrder1;
    private ProductOrder productOrder2;
    private Order mockOrder;
    private Product mockProduct;

    @BeforeEach
    public void setup() {
        mockOrder = mock(Order.class);
        mockProduct = mock(Product.class);

        productOrder1 = new ProductOrder();
        productOrder1.setId(1L);
        productOrder1.setOrder(mockOrder);
        productOrder1.setProduct(mockProduct);
        productOrder1.setPrice(BigDecimal.valueOf(100.00));

        productOrder2 = new ProductOrder();
        productOrder2.setId(1L);
        productOrder2.setOrder(mockOrder);
        productOrder2.setProduct(mockProduct);
        productOrder2.setPrice(BigDecimal.valueOf(100.00));
    }

    @Test
    public void shouldBeEquals() {
        assertEquals(productOrder1, productOrder2);
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertEquals(productOrder1.hashCode(), productOrder2.hashCode());
    }

    @Test
    public void shouldBeDifferent() {
        productOrder2.setId(2L);
        assertNotEquals(productOrder1, productOrder2);
    }

    @Test
    public void shouldTestOrderAssociation() {
        assertEquals(mockOrder, productOrder1.getOrder());
    }

    @Test
    public void shouldTestProductAssociation() {
        assertEquals(mockProduct, productOrder1.getProduct());
    }

    @Test
    public void shouldTestPrice() {
        assertEquals(BigDecimal.valueOf(100.00), productOrder1.getPrice());
    }
}
