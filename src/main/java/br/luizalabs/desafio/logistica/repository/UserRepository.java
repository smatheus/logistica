package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
