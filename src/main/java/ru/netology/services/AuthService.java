package ru.netology.services;

import org.springframework.stereotype.Service;
import ru.netology.dto.AuthRequest;

import ru.netology.entities.User;
import ru.netology.exception.AuthorizationException;
import ru.netology.repositories.UserRepository;
import ru.netology.security.SimpleSecurity;

import java.util.Random;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String loginUser(AuthRequest authRequest) {
        User currentUser = userRepository
                .getByLoginAndPassword(authRequest.getLogin(), authRequest.getPassword())
                .orElseThrow(() -> new AuthorizationException("Неверный логин или пароль"));
        String token = authRequest.getLogin() + new Random().nextLong();
        SimpleSecurity.getUsersAuthorized().put(token, currentUser);
        return token;
    }

    public void logoutUser(String authToken) {
        SimpleSecurity.getUsersAuthorized().remove(authToken);
    }
}