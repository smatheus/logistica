package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.dto.OrderDTO;
import br.luizalabs.desafio.logistica.dto.ProductDTO;
import br.luizalabs.desafio.logistica.dto.UsersOrdersDTO;
import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.entity.Product;
import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.repository.OrderRepository;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import br.luizalabs.desafio.logistica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public Map<Long, User> loadExistingUsers() {
        return userRepository.findAll().stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public void saveAllUsers(List<User> users){
        if (!users.isEmpty()) userRepository.saveAll(users);
    }

    public Page<UsersOrdersDTO> findUsersOrders(Long orderId, LocalDate startDate, LocalDate endDate, Pageable pageable) {

        Page<User> users = userRepository.findUsersWithOrder(orderId, startDate, endDate, pageable);

        List<UsersOrdersDTO> usersOrdersDTO = new ArrayList<>();

        List<User> usersList = new ArrayList<>(users.stream().toList());

        for(User user : usersList) {
            List<Order> orders = orderRepository.findOrderByUser(user.getId());

            List<OrderDTO> orderDTOS = orders.stream().map(order -> {
                List<Product> productsByOrder = productOrderRepository.findProductsByOrder(order.getId());
                return new OrderDTO(order.getId(), order.getTotalAmount(), order.getOrderDate(), productsByOrder.stream().map(product -> new ProductDTO(product.getId(), product.getPrice())).toList());
            }).collect(Collectors.toList());

            UsersOrdersDTO userOrdersDTO = new UsersOrdersDTO(user.getId(), user.getName(), orderDTOS);

            usersOrdersDTO.add(userOrdersDTO);

        }


        return new PageImpl<>(usersOrdersDTO, pageable, users.getTotalElements());

    }
}
