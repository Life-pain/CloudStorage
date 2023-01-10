package ru.netology.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.dto.AuthRequest;
import ru.netology.entities.User;
import ru.netology.exception.AuthorizationException;
import ru.netology.repositories.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthService authService;
    @Mock
    private UserRepository userRepository;
    private final String TEST_LOGIN = "test Login";
    private final String TEST_PASSWORD = "test Password";

    private final AuthRequest authRequest = new AuthRequest(TEST_LOGIN, TEST_PASSWORD);

    @Test
    void test_loginUser() {
        Mockito.when(userRepository.getByLoginAndPassword(TEST_LOGIN, TEST_PASSWORD))
                .thenReturn(Optional.of(new User(TEST_LOGIN, TEST_PASSWORD)));
        assertThat(authService.loginUser(authRequest).startsWith(TEST_LOGIN));
    }

    @Test
    void test_loginUserException() {
        Mockito.when(userRepository.getByLoginAndPassword(TEST_LOGIN, TEST_PASSWORD))
                .thenReturn(Optional.empty());
        assertThrows(AuthorizationException.class, () -> authService.loginUser(authRequest));
    }
}
