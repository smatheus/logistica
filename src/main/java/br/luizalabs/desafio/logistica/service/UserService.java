package br.luizalabs.desafio.logistica.service;

import br.luizalabs.desafio.logistica.entity.User;
import br.luizalabs.desafio.logistica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Map<Long, User> loadExistingUsers() {
        return userRepository.findAll().stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
    }

    public void saveAllUsers(List<User> users){
        if (!users.isEmpty()) userRepository.saveAll(users);
    }
}
