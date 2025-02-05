package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.dto.UsersOrdersDTO;
import br.luizalabs.desafio.logistica.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/order")
    public ResponseEntity<Page<UsersOrdersDTO>> getUserOrders(@RequestParam(value = "orderId", required = false) Long orderId,
                                                          @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                          @PageableDefault Pageable pageable){
        try {
            Page<UsersOrdersDTO> usersOrdersDTOS = userService.findUsersOrders(orderId, startDate, endDate, pageable);
            return ResponseEntity.ok(usersOrdersDTOS);
        } catch (RuntimeException e){
            LOGGER.error(e.getMessage(), e);
            throw e;
        }

    }


}
