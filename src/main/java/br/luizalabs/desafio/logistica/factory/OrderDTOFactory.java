package br.luizalabs.desafio.logistica.factory;

import br.luizalabs.desafio.logistica.dto.OrderDTO;
import br.luizalabs.desafio.logistica.dto.ProductDTO;
import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderDTOFactory {

    private final ProductOrderRepository productOrderRepository;

    public List<OrderDTO> createOrderDTOs(List<Order> orders) {
        return orders.stream()
                .map(this::createOrderDTO)
                .collect(Collectors.toList());
    }


    private OrderDTO createOrderDTO(Order order) {
        List<ProductDTO> orderProducts = productOrderRepository.findProductsDTOByOrder(order.getId());
        return new OrderDTO(order.getId(), order.getTotalAmount(), order.getOrderDate(), orderProducts);
    }


}
