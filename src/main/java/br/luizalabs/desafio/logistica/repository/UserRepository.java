package br.luizalabs.desafio.logistica.repository;

import br.luizalabs.desafio.logistica.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT DISTINCT user FROM Order order INNER JOIN order.user
            WHERE (:orderId IS NULL OR order.id = :orderId)
                AND (CAST(:startDate AS date) IS NULL OR order.orderDate >= :startDate)
                AND (CAST(:endDate AS date) IS NULL OR order.orderDate <= :endDate)
            """)
    Page<User> findUsersWithOrder(@Param("orderId") Long orderId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);

}
