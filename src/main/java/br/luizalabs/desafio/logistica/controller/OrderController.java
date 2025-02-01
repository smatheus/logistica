package br.luizalabs.desafio.logistica.controller;

import br.luizalabs.desafio.logistica.entity.Order;
import br.luizalabs.desafio.logistica.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> insertOrder(@RequestBody MultipartFile file){
        try {
            orderService.insertOrders(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
