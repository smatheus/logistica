package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}
