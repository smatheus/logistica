package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{

    @Query("SELECT order FROM Order order INNER JOIN order.user user where user.id = :userId")
    List<Order> findOrderByUser(Long userId);


}
