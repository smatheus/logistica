package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
