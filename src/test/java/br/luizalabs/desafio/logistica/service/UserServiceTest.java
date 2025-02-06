package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.dto.ProductDTO;
import br.luizalabs.desafio.logistica.dto.UsersOrdersDTO;
import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.repository.OrderRepository;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import br.luizalabs.desafio.logistica.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductOrderRepository productOrderRepository;

    @Test
    public void shouldReturnUserOrdersDTO(){
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(1L);
        user.setName("Teste");

        users.add(user);

        List<Order> orders = new ArrayList<>();

        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(new BigDecimal(200));
        order.setOrderDate(LocalDate.of(2021, 9, 9));
        order.setUser(user);

        orders.add(order);

        List<ProductDTO> products = new ArrayList<>();

        ProductDTO productDTO = new ProductDTO(1L, new BigDecimal(200));

        products.add(productDTO);

        when(userRepository.findUsersWithOrder(anyLong(), any(), any(), any())).thenReturn(new PageImpl<>(users));
        when(orderRepository.findOrderByUser(anyLong(), anyLong())).thenReturn(orders);
        when(productOrderRepository.findProductsDTOByOrder(anyLong())).thenReturn(products);

        Page<UsersOrdersDTO> usersOrders = userService.findUsersOrders(1L, LocalDate.of(2021, 9, 9), LocalDate.of(2021, 9, 13), PageRequest.of(0, 10));

        assertEquals(1L, usersOrders.get().count());
    }

    @Test
    public void shouldCatchExceptionAndReturnRuntimeException(){
        when(userRepository.findUsersWithOrder(anyLong(), any(), any(), any())).thenThrow(new IllegalArgumentException());

        assertThrows(RuntimeException.class, () -> userService.findUsersOrders(null, null, null, null));
    }

}
