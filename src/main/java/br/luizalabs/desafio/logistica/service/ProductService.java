package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.entity.Product;
import br.luizalabs.desafio.logistica.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Map<Long, Product> loadExistingProducts() {
        return productRepository.findAll().stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    public void saveAllProducts(List<Product> products){
        if (!products.isEmpty()) productRepository.saveAll(products);
    }
}
