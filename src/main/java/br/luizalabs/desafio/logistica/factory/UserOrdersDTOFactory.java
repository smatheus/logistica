package br.luizalabs.desafio.logistica.factory;

import br.luizalabs.desafio.logistica.dto.OrderDTO;
import br.luizalabs.desafio.logistica.dto.UsersOrdersDTO;
import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.repository.OrderRepository;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserOrdersDTOFactory {

    private final OrderRepository orderRepository;

    private final OrderDTOFactory orderDTOFactory;

    private final Long orderId;

    public UserOrdersDTOFactory(OrderRepository orderRepository, ProductOrderRepository productOrderRepository, Long orderId){
        this.orderRepository = orderRepository;
        this.orderDTOFactory = new OrderDTOFactory(productOrderRepository);
        this.orderId = orderId;
    }

    public UsersOrdersDTO createUserOrdersDTO(User user) {
        List<Order> orders = orderRepository.findOrderByUser(user.getId(), this.orderId);
        List<OrderDTO> orderDTOS = orderDTOFactory.createOrderDTOs(orders);
        return new UsersOrdersDTO(user.getId(), user.getName(), orderDTOS);
    }

}
