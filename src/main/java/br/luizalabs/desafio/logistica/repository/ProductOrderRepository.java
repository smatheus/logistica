package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.Product;
import br.luizalabs.desafio.logistica.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query("SELECT productsOrders.product FROM ProductOrder productsOrders where productsOrders.order.id = :orderId")
    List<Product> findProductsByOrder(Long orderId);
}
