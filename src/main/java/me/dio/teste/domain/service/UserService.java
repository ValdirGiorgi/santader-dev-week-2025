package me.dio.teste.domain.service;

import me.dio.teste.domain.model.User;

public interface  UserService {
    User findById(Long id);
    User create(User user);
}
