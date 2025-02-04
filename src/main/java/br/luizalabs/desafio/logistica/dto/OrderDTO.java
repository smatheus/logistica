package br.luizalabs.desafio.logistica.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDTO(Long orderId, BigDecimal total, LocalDate date, List<ProductDTO> productsDTO) {
}
