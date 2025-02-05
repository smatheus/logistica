package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.dto.ProductDTO;
import br.luizalabs.desafio.logistica.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query("SELECT new br.luizalabs.desafio.logistica.dto.ProductDTO(product.id, sum(productsOrders.price)) FROM ProductOrder productsOrders inner join productsOrders.product product where productsOrders.order.id = :orderId group by product.id")
    List<ProductDTO> findProductsDTOByOrder(Long orderId);
}
