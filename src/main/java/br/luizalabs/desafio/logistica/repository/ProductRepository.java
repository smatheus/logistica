package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
