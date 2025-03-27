package me.dio.teste.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.teste.domain.model.User;
import me.dio.teste.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        logger.info("Buscando usuário com ID: {}", id);
        User user = userService.findById(id);
        logger.info("Usuário encontrado: {}", user != null ? user.getName() : "null");
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        logger.info("Recebida requisição para criar usuário: {}", 
                userToCreate != null ? userToCreate.getName() : "null");
        
        if (userToCreate == null) {
            logger.error("Corpo da requisição está vazio");
            throw new IllegalArgumentException("Corpo da requisição não pode estar vazio");
        }
        
        try {
            logger.debug("Dados da conta: {}", userToCreate.getAccount() != null ? 
                userToCreate.getAccount().getNumber() : "null");
            
            User createdUser = userService.create(userToCreate);
            logger.info("Usuário criado com sucesso: {}", createdUser.getId());
            
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdUser.getId())
                    .toUri();
            
            return ResponseEntity.created(location).body(createdUser);
        } catch (Exception e) {
            logger.error("Erro ao criar usuário: {}", e.getMessage(), e);
            throw e;
        }
    }
}