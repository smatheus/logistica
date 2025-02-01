package br.luizalabs.desafio.logistica.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "products")
@Data
public class Product {
    @Id
    private Long id;
    private BigDecimal value;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductOrder> orderedProducts = new HashSet<>();
    public void setOrderedProducts(ProductOrder orderedProduct) {
        this.orderedProducts.add(orderedProduct);
    }
}
