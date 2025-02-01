package br.luizalabs.desafio.logistica.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {

    @Id
    private Long id;

    private String name;


}
