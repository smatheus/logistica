package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Map<Long, Order> loadExistingOrders() {
        return orderRepository.findAll().stream()
                .collect(Collectors.toMap(Order::getId, Function.identity()));
    }

    public void saveAllOrders(List<Order> orders){
        if (!orders.isEmpty()) orderRepository.saveAll(orders);
    }

}
