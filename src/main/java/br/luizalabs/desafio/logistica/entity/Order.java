package br.luizalabs.desafio.logistica.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "orders")
@Data
public class Order {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductOrder> orderedProducts = new HashSet<>();

    private BigDecimal totalAmount = BigDecimal.ZERO;

    private LocalDate orderDate;
    public void setOrderedProducts(ProductOrder orderedProduct) {
        this.orderedProducts.add(orderedProduct);
    }

    public void setTotalAmount(BigDecimal amount){
        this.totalAmount = this.totalAmount.add(amount);
    }
}
