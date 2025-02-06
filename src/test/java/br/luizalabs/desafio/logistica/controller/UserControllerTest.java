package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    public void shouldCallServiceToGetUserOrders(){
        Long orderId = 1L;
        LocalDate startDate= LocalDate.of(2021, 9, 9);
        LocalDate endDate = LocalDate.of(2021, 9, 12);
        Pageable page = PageRequest.of(0, 10);

        userController.getUserOrders(orderId, startDate, endDate, page);

        verify(userService, times(1)).findUsersOrders(orderId, startDate, endDate, page);
    }

    @Test
    public void shoudLoggerAndFailWhenUserServiceFails() throws RuntimeException {
        Long orderId = 1L;
        LocalDate startDate= LocalDate.of(2021, 9, 9);
        LocalDate endDate = LocalDate.of(2021, 9, 12);
        Pageable page = PageRequest.of(0, 10);
        when(userService.findUsersOrders(anyLong(), any(), any(), any())).thenThrow(new RuntimeException("Something went wrong"));
        try {
            userController.getUserOrders(orderId, startDate, endDate, page);
        }catch(RuntimeException e) {
            assertEquals(e.getMessage(), "Something went wrong");
        }
    }

}
