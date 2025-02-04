package br.luizalabs.desafio.logistica.dto;

import java.util.List;

public record UsersOrdersDTO(Long userId, String name, List<OrderDTO> ordersDTO) {
}
