package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.entity.ProductOrder;
import br.luizalabs.desafio.logistica.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public void saveAllProductsOrders(List<ProductOrder> productsOrders){
        if (!productsOrders.isEmpty()) productOrderRepository.saveAll(productsOrders);
    }

}
