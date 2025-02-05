package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT order FROM Order order INNER JOIN order.user user where user.id = :userId AND (:orderId IS NULL OR order.id = :orderId)")
    List<Order> findOrderByUser(@Param("userId") Long userId, @Param("orderId") Long orderId);


}
