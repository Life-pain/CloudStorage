package ru.netology.security;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.netology.exception.AuthorizationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimpleSecurityTest {
    private final String TEST_LOGIN = "test Login";
    private final String TEST_TOKEN = "test Token";

    @Test
    void getOwnerByToken() {
        try (MockedStatic<SimpleSecurity> utilities = Mockito.mockStatic(SimpleSecurity.class)) {
            utilities.when(() -> SimpleSecurity.getOwnerByToken(TEST_TOKEN))
                    .thenReturn(TEST_LOGIN);
            assertEquals(SimpleSecurity.getOwnerByToken(TEST_TOKEN), TEST_LOGIN);
        }
    }

    @Test
    void getOwnerByTokenException() {
        assertThrows(AuthorizationException.class, () -> SimpleSecurity.getOwnerByToken(TEST_TOKEN));
    }
}
